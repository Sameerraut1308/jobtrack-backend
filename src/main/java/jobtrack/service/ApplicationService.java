package jobtrack.service;

import jobtrack.entity.Application;
import jobtrack.entity.Job;
import jobtrack.entity.User;
import jobtrack.enums.ApplicationStatus;
import jobtrack.repository.ApplicationRepository;
import jobtrack.repository.JobRepository;
import jobtrack.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final CurrentUserService currentUserService;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            JobRepository jobRepository,
            CurrentUserService currentUserService) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.currentUserService = currentUserService;
    }

    // Apply for a job as the logged-in user
    public Application applyForJob(Long jobId, String notes) {
        User currentUser = currentUserService.getCurrentUser();

        // Check if already applied
        if (applicationRepository.findByUserIdAndJobId(currentUser.getId(), jobId).isPresent()) {
            throw new RuntimeException("You have already applied for this job!");
        }

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));

        Application application = new Application();
        application.setUser(currentUser);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);
        application.setNotes(notes);

        return applicationRepository.save(application);
    }

    // Get all applications of the currently logged-in user
    public List<Application> getMyApplications() {
        User currentUser = currentUserService.getCurrentUser();
        return applicationRepository.findByUserId(currentUser.getId());
    }

    // Get all applications for a specific job
    public List<Application> getApplicationsByJobId(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    // Update application status
    public Application updateStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + applicationId));

        application.setStatus(status);
        return applicationRepository.save(application);
    }

    // Delete / Withdraw application
    public void withdrawApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + applicationId));

        applicationRepository.delete(application);
    }
}