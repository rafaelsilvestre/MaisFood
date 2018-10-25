package br.com.omaisfood.endpoint;

import br.com.omaisfood.dto.CompanyForm;
import br.com.omaisfood.dto.CompanyFormEdit;
import br.com.omaisfood.dto.WorkedDaysForm;
import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.User;
import br.com.omaisfood.model.WorkedWeek;
import br.com.omaisfood.service.CompanyService;
import br.com.omaisfood.service.UserService;
import br.com.omaisfood.service.WorkedWeekService;
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
    private WorkedWeekService workedDayService;

    @Autowired
    private UserService userService;

    CompanyEndPoint(CompanyService companyService, WorkedWeekService workedDayService, UserService userService) {
        this.companyService = companyService;
        this.workedDayService = workedDayService;
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(this.companyService.getAllCompanies(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Company> saveCompany(@RequestBody @Valid CompanyForm companyForm) {
        Company company = Company.fromCompanyForm(companyForm);
        User user = User.fromCompanyForm(companyForm);

        // Save this company
        Company newCompany = this.companyService.saveCompany(company, user);
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

    @CrossOrigin
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
        Company company = this.companyService.updateCompany(Company.fromCompanyFormEdit(companyFormEdit), companyId);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @GetMapping(path = "/{companyId}/worked-days")
    public ResponseEntity<List<WorkedWeek>> getWorkedDayByCompany(@PathVariable Long companyId) {
        List<WorkedWeek> days = this.workedDayService.getWorkedDayByCompany(companyId);
        return new ResponseEntity<List<WorkedWeek>>(days, HttpStatus.OK);
    }

    @PostMapping(path = "/{companyId}/worked-days")
    public ResponseEntity<List<WorkedWeek>> saveWorkedDayByCompany(@RequestBody @Valid WorkedDaysForm workedDaysForm, @PathVariable Long companyId) {
        Company company = this.companyService.findById(companyId);
        //company.setWorkedDays(workedDaysForm.getWorkedDays());

        return null;
    }
}