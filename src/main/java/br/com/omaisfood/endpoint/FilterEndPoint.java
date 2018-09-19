package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Filter;
import br.com.omaisfood.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("filter")
public class FilterEndPoint {
    @Autowired
    private FilterService filterService;

    FilterEndPoint(FilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping
    public ResponseEntity<List<Filter>> getAllFilter(){
        return new ResponseEntity<List<Filter>>(this.filterService.getAllFilters(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Filter> saveFilter(@RequestBody Filter filter) {
        return new ResponseEntity<Filter>(this.filterService.saveFilter(filter), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteFilter(@PathVariable Long id) {
        Filter filter = this.filterService.find(id);
        this.filterService.deleteFilter(filter);
        return ResponseEntity.noContent().build();
    }
}
