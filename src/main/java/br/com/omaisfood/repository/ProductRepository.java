package br.com.omaisfood.repository;

import br.com.omaisfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCompanyId(Long companyId);

}
