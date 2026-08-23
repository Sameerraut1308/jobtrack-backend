package jobtrack.entity;

import jobtrack.enums.ResumeType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String filePath;

    @Enumerated(EnumType.STRING)
    private ResumeType type;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Resume() {

    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
