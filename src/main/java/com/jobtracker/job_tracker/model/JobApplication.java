package com.jobtracker.job_tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String role;
    private String status;
    private LocalDate dateApplied;
    private String jobDescription;

    @Column(length = 2000)
    private String aiFeedback;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}