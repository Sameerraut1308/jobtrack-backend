package jobtrack.controller;

import jobtrack.entity.Company;
import jobtrack.service.CompanyService;
import jobtrack.security.CurrentUserService;
import jobtrack.entity.User;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CurrentUserService currentUserService;

    public CompanyController(CompanyService companyService, CurrentUserService currentUserService) {
        this.companyService = companyService;
        this.currentUserService = currentUserService;

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
    public Company saveCompany(@RequestBody Company company, Authentication authentication) {

        System.out.println("Authenticated user: " + authentication.getPrincipal());
        return companyService.saveCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(
            @PathVariable Long id,
            @RequestBody Company company) {

        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }

}
