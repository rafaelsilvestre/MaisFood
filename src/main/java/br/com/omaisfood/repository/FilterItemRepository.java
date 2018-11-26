package br.com.omaisfood.repository;

import br.com.omaisfood.model.FilterItem;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterItemRepository extends JpaRepository<FilterItem, Long> {
    List<FilterItem> findAllByCompanyId(Long companyId);

    Boolean existsByCompanyIdAndFilterId(Long companyId, Long filterId);
}
