package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.service.CompanyService;
import br.com.omaisfood.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyEndPoint {
    @Autowired
    private CompanyService companyService;

    CompanyEndPoint(CompanyService companyService) {
        this.companyService = companyService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(this.companyService.getAllCompanies(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Company> saveCompany(@Valid @RequestBody Company company) {
        return new ResponseEntity<Company>(this.companyService.saveCompany(company), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        try{
            Company company = companyService.find(id);
            companyService.deleteCompany(company);
        }catch (ObjectNotFoundException e){

        }

        return ResponseEntity.noContent().build();
    }
}
