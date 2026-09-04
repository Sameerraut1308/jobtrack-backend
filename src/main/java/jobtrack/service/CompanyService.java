package jobtrack.service;

import jobtrack.dto.CompanyRequest;
import jobtrack.entity.Company;
import jobtrack.exception.ResourceNotFoundException;
import jobtrack.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(CompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setType(request.getType());
        company.setWebsite(request.getWebsite());
        company.setFoundedDate(request.getFoundedDate());

        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
    }

    public Company updateCompany(Long id, CompanyRequest request) {
        Company existingCompany = getCompanyById(id);

        existingCompany.setName(request.getName());
        existingCompany.setType(request.getType());
        existingCompany.setWebsite(request.getWebsite());
        existingCompany.setFoundedDate(request.getFoundedDate());

        return companyRepository.save(existingCompany);
    }

    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }
}