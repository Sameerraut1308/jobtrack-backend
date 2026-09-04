package jobtrack.controller;

import jobtrack.entity.Resume;
import jobtrack.enums.ResumeType;
import jobtrack.service.ResumeService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    // Upload a resume: POST /api/resumes/upload
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Resume uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "type", required = false, defaultValue = "PDF") ResumeType type) {
        return resumeService.uploadResume(file, type);
    }

    // Get all my uploaded resumes: GET /api/resumes
    @GetMapping
    public List<Resume> getMyResumes() {
        return resumeService.getMyResumes();
    }

    // Download a resume: GET /api/resumes/download/{id}
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadResume(@PathVariable Long id) {
        Resource resource = resumeService.downloadResume(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // Delete a resume: DELETE /api/resumes/{id}
    @DeleteMapping("/{id}")
    public void deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
    }
}