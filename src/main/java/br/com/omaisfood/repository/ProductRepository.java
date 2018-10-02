package br.com.omaisfood.repository;

import br.com.omaisfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
