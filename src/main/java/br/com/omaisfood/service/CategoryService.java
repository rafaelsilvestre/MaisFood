package br.com.omaisfood.service;

import br.com.omaisfood.model.Category;
import br.com.omaisfood.model.Company;
import br.com.omaisfood.repository.CategoryRepository;
import br.com.omaisfood.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompanyService companyService;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        Company company = this.companyService.getCompanyByUserLogged();
        category.setCompany(company);

        return this.categoryRepository.save(category);
    }

    public List<Category> getAllCategoriesByCompanyLogged() {
        Company company = this.companyService.getCompanyByUserLogged();
        return this.categoryRepository.findCategoriesByCompany(company);
    }
}
