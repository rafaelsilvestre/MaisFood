package br.com.omaisfood.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryForm {
    @NotEmpty(message = "Informe um nome para a categoria")
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
