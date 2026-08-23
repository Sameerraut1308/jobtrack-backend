package jobtrack.service;

import jobtrack.entity.Company;
import jobtrack.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(Company comapny) {
        return companyRepository.save(comapny);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
