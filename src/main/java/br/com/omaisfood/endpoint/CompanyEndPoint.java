package br.com.omaisfood.endpoint;

import br.com.omaisfood.dto.CompanyForm;
import br.com.omaisfood.dto.CompanyFormEdit;
import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.Filter;
import br.com.omaisfood.model.FilterItem;
import br.com.omaisfood.model.User;
import br.com.omaisfood.service.*;
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

    @Autowired
    private FilterItemService filterItemService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(this.companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> saveCompany(@RequestBody @Valid CompanyForm companyForm) {
        Company company = Company.fromCompanyForm(companyForm);
        User user = User.fromCompanyForm(companyForm);

        // Save this company
        Company newCompany = this.companyService.saveCompany(company, user);

        companyForm.getCategories().forEach(category -> {
            Filter filter = new Filter(category.getId(), category.getName());
            // Save this Filte in company
            this.filterItemService.save(new FilterItem(filter, newCompany));
        });

        this.emailService.sendEmail();

        return new ResponseEntity<Company>(newCompany, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = this.companyService.findById(id);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @GetMapping(path = "/data")
    public ResponseEntity<Company> getCompanyByUser() {
        Company company = this.companyService.getCompanyByUserLogged();
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        try{
            Company company = companyService.findById(id);
            companyService.deleteCompany(company);
        }catch (ObjectNotFoundException e){

        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{companyId}")
    public ResponseEntity<Company> updateCompany(@RequestBody @Valid CompanyFormEdit companyFormEdit, @PathVariable Long companyId) {
        Company company = this.companyService.updateCompany(Company.fromCompanyFormEdit(companyFormEdit), companyFormEdit.getCategories(), companyId);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @GetMapping(path = "/district/{districtId}")
    public ResponseEntity<List<Company>> getAllCompaniesByDistrict(@PathVariable Long districtId) {
        return new ResponseEntity<>(this.companyService.getCompanyByDistrinct(districtId), HttpStatus.OK);
    }
}