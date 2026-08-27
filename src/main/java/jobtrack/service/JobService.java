package jobtrack.service;

import jobtrack.entity.Job;
import jobtrack.entity.Company;
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

    public Job getJobById(Long Id) {
        return jobRepository.findById(Id).orElseThrow();
    }

    public Job saveJob(Long companyId, Job job) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        job.setCompany(company);

        return jobRepository.save(job);
    }

    public Job updateJob(Long id, Long companyId, Job job) {

        Job existingJob = jobRepository.findById(id)
                .orElseThrow();

        Company company = companyRepository.findById(companyId)
                .orElseThrow();

        existingJob.setCompany(company);
        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setLocation(job.getLocation());
        existingJob.setSalary(job.getSalary());
        existingJob.setJobUrl(job.getJobUrl());
        existingJob.setPostedDate(job.getPostedDate());

        return jobRepository.save(existingJob);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
