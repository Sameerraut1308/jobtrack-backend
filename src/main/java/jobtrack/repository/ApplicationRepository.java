package jobtrack.repository;

import jobtrack.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Find all applications submitted by a specific user
    List<Application> findByUserId(Long userId);

    // Find all applications for a specific job
    List<Application> findByJobId(Long jobId);

    // Check if user already applied to this job
    Optional<Application> findByUserIdAndJobId(Long userId, Long jobId);
}