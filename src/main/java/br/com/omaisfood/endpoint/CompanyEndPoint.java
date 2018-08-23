package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        return new ResponseEntity<Company>(this.companyService.saveCompany(company), HttpStatus.OK);
    }
}
