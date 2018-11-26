package br.com.omaisfood.repository;

import br.com.omaisfood.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
    List<Filter> findAllByOrderByIdDesc();

}
