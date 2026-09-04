package jobtrack.service;

import jobtrack.entity.Resume;
import jobtrack.entity.User;
import jobtrack.enums.ResumeType;
import jobtrack.exception.BadRequestException;
import jobtrack.exception.ResourceNotFoundException;
import jobtrack.repository.ResumeRepository;
import jobtrack.security.CurrentUserService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final CurrentUserService currentUserService;
    private final Path uploadDirectory = Paths.get("uploads/resumes");

    public ResumeService(ResumeRepository resumeRepository, CurrentUserService currentUserService) {
        this.resumeRepository = resumeRepository;
        this.currentUserService = currentUserService;

        // Automatically create upload directory if it does not exist
        try {
            Files.createDirectories(uploadDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize upload folder", e);
        }
    }

    // Upload a resume file
    public Resume uploadResume(MultipartFile file, ResumeType type) {
        if (file.isEmpty()) {
            throw new BadRequestException("Uploaded file cannot be empty");
        }

        User currentUser = currentUserService.getCurrentUser();

        // Create a unique filename to prevent overwriting
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID() + "_" + (originalFilename != null ? originalFilename : "resume");
        Path destination = uploadDirectory.resolve(uniqueFilename);

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }

        Resume resume = new Resume();
        resume.setUser(currentUser);
        resume.setFilePath(destination.toString());
        resume.setType(type != null ? type : ResumeType.PDF);

        return resumeRepository.save(resume);
    }

    // Get all resumes of the current user
    public List<Resume> getMyResumes() {
        User currentUser = currentUserService.getCurrentUser();
        return resumeRepository.findByUserId(currentUser.getId());
    }

    // Download a resume file
    public Resource downloadResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found with id: " + resumeId));

        try {
            Path filePath = Paths.get(resume.getFilePath());
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("File not found on disk");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage());
        }
    }

    // Delete a resume
    public void deleteResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found with id: " + resumeId));

        // Delete physical file
        try {
            Files.deleteIfExists(Paths.get(resume.getFilePath()));
        } catch (IOException ignored) {
        }

        resumeRepository.delete(resume);
    }
}