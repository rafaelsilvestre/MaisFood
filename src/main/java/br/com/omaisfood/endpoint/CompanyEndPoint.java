package br.com.omaisfood.endpoint;

import br.com.omaisfood.dto.CompanyForm;
import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.Filter;
import br.com.omaisfood.model.User;
import br.com.omaisfood.service.CompanyService;
import br.com.omaisfood.service.UserService;
import br.com.omaisfood.service.exception.EmailExistsException;
import br.com.omaisfood.service.exception.ErrorCreatingUserException;
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
    private UserService userService;

    CompanyEndPoint(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(this.companyService.getAllCompanies(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> saveCompany(@RequestBody @Valid CompanyForm companyForm) {
        Company company = Company.fromCompanyForm(companyForm);
        User user = User.fromCompanyForm(companyForm);

        try{
            Company newCompany = this.companyService.saveCompany(company, user);
        }catch (ErrorCreatingUserException e){
            System.out.println("Erro ao criar usuário - " + e);
        } catch (EmailExistsException e){
            System.out.println("Erro ao criar usuário - " + e);
        }

        return new ResponseEntity<CompanyForm>(companyForm, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = this.companyService.find(id);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
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