package br.com.omaisfood.repository;

import br.com.omaisfood.model.Category;
import br.com.omaisfood.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoriesByCompany(Company company);
}
