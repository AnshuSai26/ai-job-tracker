package com.jobtracker.job_tracker.controller;

import com.jobtracker.job_tracker.model.JobApplication;
import com.jobtracker.job_tracker.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public JobApplication addJob(@RequestBody JobApplication job,
                                 Authentication auth) {
        return jobService.addJob(job, auth.getName());
    }

    @GetMapping
    public List<JobApplication> getJobs(Authentication auth) {
        return jobService.getJobs(auth.getName());
    }

    @PutMapping("/{id}")
    public JobApplication updateStatus(@PathVariable Long id,
                                       @RequestBody Map<String, String> body) {
        return jobService.updateStatus(id, body.get("status"));
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Deleted successfully";
    }
}
