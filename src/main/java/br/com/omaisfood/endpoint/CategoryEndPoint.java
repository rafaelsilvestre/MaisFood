package br.com.omaisfood.endpoint;

import br.com.omaisfood.dto.CategoryForm;
import br.com.omaisfood.model.Category;
import br.com.omaisfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryEndPoint {

    @Autowired
    private CategoryService categoryService;

    CategoryEndPoint(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = this.categoryService.getAllCategoriesByCompanyLogged();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody @Valid CategoryForm categoryForm) {
        Category category = this.categoryService.saveCategory(Category.fromCategoryForm(categoryForm));
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }
}
