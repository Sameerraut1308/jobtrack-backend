package jobtrack.service;

import jakarta.transaction.Transactional;
import jobtrack.entity.Job;
import jobtrack.entity.SavedJob;
import jobtrack.entity.User;
import jobtrack.exception.BadRequestException;
import jobtrack.exception.ResourceNotFoundException;
import jobtrack.repository.JobRepository;
import jobtrack.repository.SavedJobRepository;
import jobtrack.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobService {

    private final SavedJobRepository savedJobRepository;
    private final JobRepository jobRepository;
    private final CurrentUserService currentUserService;

    public SavedJobService(
            SavedJobRepository savedJobRepository,
            JobRepository jobRepository,
            CurrentUserService currentUserService) {
        this.savedJobRepository = savedJobRepository;
        this.jobRepository = jobRepository;
        this.currentUserService = currentUserService;
    }

    // Save/Bookmark a job
    public SavedJob saveJob(Long jobId) {
        User currentUser = currentUserService.getCurrentUser();

        // Check if already saved
        if (savedJobRepository.findByUserIdAndJobId(currentUser.getId(), jobId).isPresent()) {
            throw new BadRequestException("You have already saved this job!");
        }

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));

        SavedJob savedJob = new SavedJob();
        savedJob.setUser(currentUser);
        savedJob.setJob(job);

        return savedJobRepository.save(savedJob);
    }

    // Get all bookmarked jobs for the logged-in user
    public List<SavedJob> getMySavedJobs() {
        User currentUser = currentUserService.getCurrentUser();
        return savedJobRepository.findByUserId(currentUser.getId());
    }

    // Remove a saved job bookmark
    @Transactional
    public void unsaveJob(Long jobId) {
        User currentUser = currentUserService.getCurrentUser();

        if (savedJobRepository.findByUserIdAndJobId(currentUser.getId(), jobId).isEmpty()) {
            throw new ResourceNotFoundException("Saved job not found for job id: " + jobId);
        }

        savedJobRepository.deleteByUserIdAndJobId(currentUser.getId(), jobId);
    }
}