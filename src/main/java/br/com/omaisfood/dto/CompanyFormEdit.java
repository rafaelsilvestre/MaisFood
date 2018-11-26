package br.com.omaisfood.dto;

import br.com.omaisfood.model.Filter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompanyFormEdit {
    @NotEmpty(message = "O nome deve ser informado!")
    private String companyName;

    @NotEmpty(message = "A descrição deve ser informada!")
    private String description;

    @NotNull(message = "O valor mínimo deve ser informado!")
    private BigDecimal minimumValue;

    private List<Filter> categories = new ArrayList<Filter>();

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

    public List<Filter> getCategories() {
        return categories;
    }

    public void setCategories(List<Filter> categories) {
        this.categories = categories;
    }
}
