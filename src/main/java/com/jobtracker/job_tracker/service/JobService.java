package com.jobtracker.job_tracker.service;
import com.jobtracker.job_tracker.service.OpenAIService;
import com.jobtracker.job_tracker.model.JobApplication;
import com.jobtracker.job_tracker.model.User;
import com.jobtracker.job_tracker.repository.JobRepository;
import com.jobtracker.job_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OpenAIService openAIService;

    public JobApplication addJob(JobApplication job, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        job.setUser(user);

        // AI analysis if job description provided
        if (job.getJobDescription() != null
                && !job.getJobDescription().isEmpty()) {
            String feedback = openAIService
                    .analyzeJobDescription(job.getJobDescription());
            job.setAiFeedback(feedback);
        }

        return jobRepository.save(job);
    }

    public List<JobApplication> getJobs(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return jobRepository.findByUser(user);
    }

    public JobApplication updateStatus(Long id, String status) {
        JobApplication job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setStatus(status);
        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
