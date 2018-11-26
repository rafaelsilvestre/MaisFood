package br.com.omaisfood.service;

import br.com.omaisfood.model.FilterItem;
import br.com.omaisfood.repository.FilterItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterItemService {

    @Autowired
    private FilterItemRepository filterItemRepository;

    public FilterItem save(FilterItem filterItem) {
        return this.filterItemRepository.save(filterItem);
    }

    public void removeFilterItem(Long companyId) {
        List<FilterItem> filters = this.filterItemRepository.findAllByCompanyId(companyId);
        this.filterItemRepository.deleteAll(filters);
    }

    public Boolean filterExists(Long companyId, Long filterId) {
        return this.filterItemRepository.existsByCompanyIdAndFilterId(companyId, filterId);
    }
}
