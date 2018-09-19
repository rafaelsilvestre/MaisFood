package br.com.omaisfood.service;

import br.com.omaisfood.model.Filter;
import br.com.omaisfood.repository.FilterRepository;
import br.com.omaisfood.service.exception.DataIntegrityException;
import br.com.omaisfood.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService {
    @Autowired
    private FilterRepository filterRepository;

    FilterService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    public List<Filter> getAllFilters() {
        return this.filterRepository.findAll();
    }

    public Filter saveFilter(Filter filter) {
        return this.filterRepository.save(filter);
    }

    public void deleteFilter(Filter filter) {
        try{
            this.filterRepository.delete(filter);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir o filtro");
        }
    }

    public Filter find(Long id) {
        Boolean isExists = this.filterRepository.existsById(id);
        if (!isExists){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
        }

        return this.filterRepository.findById(id).get();
    }
}
