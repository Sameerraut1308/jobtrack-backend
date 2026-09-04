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

    // Get all jobs or filter by keyword & location: GET /api/jobs?keyword=java&location=remote
    @GetMapping
    public List<Job> getJobs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location) {
        if (keyword != null || location != null) {
            return jobService.searchJobs(keyword, location);
        }
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // Get all jobs of a company: GET /api/jobs/company/{companyId}
    @GetMapping("/company/{companyId}")
    public List<Job> getJobsByCompany(@PathVariable Long companyId) {
        return jobService.getJobsByCompany(companyId);
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