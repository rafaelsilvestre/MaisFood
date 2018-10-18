package br.com.omaisfood.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CompanyFormEdit {
    @NotEmpty(message = "O nome deve ser informado!")
    private String companyName;

    @NotEmpty(message = "A descrição deve ser informada!")
    private String description;

    @NotNull(message = "O valor mínimo deve ser informado!")
    private BigDecimal minimumValue;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(BigDecimal minimumValue) {
        this.minimumValue = minimumValue;
    }
}
