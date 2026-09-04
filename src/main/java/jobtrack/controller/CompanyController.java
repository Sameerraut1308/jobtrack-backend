package jobtrack.controller;

import jakarta.validation.Valid;
import jobtrack.dto.CompanyRequest;
import jobtrack.entity.Company;
import jobtrack.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public Company saveCompany(@Valid @RequestBody CompanyRequest request) {
        return companyService.saveCompany(request);
    }

    @PutMapping("/{id}")
    public Company updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request) {
        return companyService.updateCompany(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}