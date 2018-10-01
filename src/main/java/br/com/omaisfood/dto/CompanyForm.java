package br.com.omaisfood.dto;

import br.com.omaisfood.model.Filter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompanyForm {
    @NotEmpty(message = "O nome deve ser informado!")
    private String companyName;

    @NotEmpty(message = "A descrição deve ser informada!")
    private String description;

    @NotNull(message = "O valor mínimo deve ser informado!")
    private BigDecimal minimumValue;

    @NotEmpty(message = "Selecione um filtro!")
    private List<Filter> categories = new ArrayList<Filter>();

    @NotEmpty(message = "O nome do proprietário deve ser informado!")
    private String ownerName;

    @NotEmpty(message = "O sobrenome do proprietário deve ser informado!")
    private String ownerLastname;

    @Email
    @NotEmpty(message = "O email do proprietário deve ser informado!")
    private String email;

    @NotEmpty(message = "A senha de acesso deve ser informada!")
    private String  password;

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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerLastname() {
        return ownerLastname;
    }

    public void setOwnerLastname(String ownerLastname) {
        this.ownerLastname = ownerLastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
