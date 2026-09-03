package jobtrack.controller;

import jobtrack.entity.Application;
import jobtrack.enums.ApplicationStatus;
import jobtrack.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Apply for a job: POST /api/applications/{jobId}
    @PostMapping("/{jobId}")
    public Application applyForJob(
            @PathVariable Long jobId,
            @RequestParam(required = false) String notes) {
        return applicationService.applyForJob(jobId, notes);
    }

    // Get my submitted applications: GET /api/applications/my
    @GetMapping("/my")
    public List<Application> getMyApplications() {
        return applicationService.getMyApplications();
    }

    // Get all applications for a specific job: GET /api/applications/job/{jobId}
    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJobId(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJobId(jobId);
    }

    // Update application status: PUT /api/applications/{id}/status?status=INTERVIEW
    @PutMapping("/{id}/status")
    public Application updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status) {
        return applicationService.updateStatus(id, status);
    }

    // Withdraw application: DELETE /api/applications/{id}
    @DeleteMapping("/{id}")
    public void withdrawApplication(@PathVariable Long id) {
        applicationService.withdrawApplication(id);
    }
}