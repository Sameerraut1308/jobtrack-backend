package jobtrack.controller;

import jakarta.validation.Valid;
import jobtrack.dto.JobRequest;
import jobtrack.entity.Job;
import jobtrack.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @PostMapping
    public Job saveJob(@Valid @RequestBody JobRequest request) {
        return jobService.saveJob(request);
    }

    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequest request) {
        return jobService.updateJob(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}