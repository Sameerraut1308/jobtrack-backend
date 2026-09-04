package jobtrack.repository;

import jobtrack.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    // Find all resumes uploaded by a specific user
    List<Resume> findByUserId(Long userId);
}