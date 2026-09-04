package jobtrack.repository;

import jobtrack.entity.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {

    // Get all jobs saved by the user
    List<SavedJob> findByUserId(Long userId);

    // Check if user already saved this job
    Optional<SavedJob> findByUserIdAndJobId(Long userId, Long jobId);

    // Remove saved job by userId and jobId
    void deleteByUserIdAndJobId(Long userId, Long jobId);
}