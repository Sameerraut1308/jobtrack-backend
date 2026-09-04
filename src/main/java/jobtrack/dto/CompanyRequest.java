package jobtrack.dto;

import jobtrack.enums.CompanyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    private String name;

    @NotNull(message = "Company type is required (PRODUCT or SERVICE)")
    private CompanyType type;

    private String website;

    private LocalDate foundedDate;

    public CompanyRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDate getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(LocalDate foundedDate) {
        this.foundedDate = foundedDate;
    }
}