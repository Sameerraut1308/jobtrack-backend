package jobtrack.service;

import jobtrack.entity.SavedJob;
import jobtrack.repository.SavedJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobService {

    private final SavedJobRepository savedJobRepository;

    public SavedJobService(SavedJobRepository savedJobRepository) {
        this.savedJobRepository = savedJobRepository;
    }

    public SavedJob saveSavedJob(SavedJob savedJob) {
        return savedJobRepository.save(savedJob);
    }

    public List<SavedJob> getAllSavedJobs() {
        return savedJobRepository.findAll();
    }
}