package br.com.omaisfood.repository;

import br.com.omaisfood.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterRepository extends JpaRepository<Filter, Long> {
    List<Filter> findAllByOrderByIdDesc();

}
