package jobtrack.service;

import jobtrack.dto.JobRequest;
import jobtrack.entity.Company;
import jobtrack.entity.Job;
import jobtrack.exception.ResourceNotFoundException;
import jobtrack.repository.CompanyRepository;
import jobtrack.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
    }

    // Search jobs with optional keyword and location filters
    public List<Job> searchJobs(String keyword, String location) {
        return jobRepository.searchJobs(keyword, location);
    }

    // Get all jobs for a specific company
    public List<Job> getJobsByCompany(Long companyId) {
        return jobRepository.findByCompanyId(companyId);
    }

    public Job saveJob(JobRequest request) {
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + request.getCompanyId()));

        Job job = new Job();
        job.setCompany(company);
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setJobUrl(request.getJobUrl());
        job.setPostedDate(request.getPostedDate());

        return jobRepository.save(job);
    }

    public Job updateJob(Long id, JobRequest request) {
        Job existingJob = getJobById(id);

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + request.getCompanyId()));

        existingJob.setCompany(company);
        existingJob.setTitle(request.getTitle());
        existingJob.setDescription(request.getDescription());
        existingJob.setLocation(request.getLocation());
        existingJob.setSalary(request.getSalary());
        existingJob.setJobUrl(request.getJobUrl());
        existingJob.setPostedDate(request.getPostedDate());

        return jobRepository.save(existingJob);
    }

    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new ResourceNotFoundException("Job not found with id: " + id);
        }
        jobRepository.deleteById(id);
    }
}