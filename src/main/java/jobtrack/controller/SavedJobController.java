package jobtrack.controller;

import jobtrack.entity.SavedJob;
import jobtrack.service.SavedJobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-jobs")
public class SavedJobController {

    private final SavedJobService savedJobService;

    public SavedJobController(SavedJobService savedJobService) {
        this.savedJobService = savedJobService;
    }

    // Bookmark a job: POST /api/saved-jobs/{jobId}
    @PostMapping("/{jobId}")
    public SavedJob saveJob(@PathVariable Long jobId) {
        return savedJobService.saveJob(jobId);
    }

    // Get all bookmarked jobs: GET /api/saved-jobs
    @GetMapping
    public List<SavedJob> getMySavedJobs() {
        return savedJobService.getMySavedJobs();
    }

    // Remove bookmark: DELETE /api/saved-jobs/{jobId}
    @DeleteMapping("/{jobId}")
    public void unsaveJob(@PathVariable Long jobId) {
        savedJobService.unsaveJob(jobId);
    }
}