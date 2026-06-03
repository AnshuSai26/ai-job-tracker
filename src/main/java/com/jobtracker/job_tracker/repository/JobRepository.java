package com.jobtracker.job_tracker.repository;

import com.jobtracker.job_tracker.model.JobApplication;
import com.jobtracker.job_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUser(User user);
}