package br.com.omaisfood.dto;

import javax.validation.constraints.NotEmpty;

public class UserEditForm {
    @NotEmpty(message = "Informe seu nome")
    private String name;

    @NotEmpty(message = "Informe seu sobrenome")
    private String lastName;

    @NotEmpty(message = "Informe seu email")
    private String email;

    public UserEditForm() { }

    public UserEditForm(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
