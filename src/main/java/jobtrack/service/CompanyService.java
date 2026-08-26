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

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    public Company updateCompany(Long id, Company company) {

        Company existingCompany = getCompanyById(id);

        existingCompany.setName(company.getName());
        existingCompany.setType(company.getType());
        existingCompany.setWebsite(company.getWebsite());
        existingCompany.setFoundedDate(company.getFoundedDate());

        return companyRepository.save(existingCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
