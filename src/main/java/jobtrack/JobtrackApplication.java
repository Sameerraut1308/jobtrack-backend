package jobtrack;

import jobtrack.entity.Company;
import jobtrack.enums.CompanyType;
import jobtrack.repository.CompanyRepository;
import jobtrack.service.CompanyService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobtrackApplication implements CommandLineRunner {

    private final CompanyService companyService;

    public JobtrackApplication(CompanyService companyService) {
        this.companyService = companyService;
    }

    public static void main(String[] args) {
        SpringApplication.run(JobtrackApplication.class, args);
    }

    @Override
    public void run(String... args) {

        List<Company> companies = companyService.getAllCompanies();

        System.out.println("All companies: ");

        companies.forEach(company -> {
            System.out.println(company.getName());
        });

    }
}