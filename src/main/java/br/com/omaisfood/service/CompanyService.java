package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.User;
import br.com.omaisfood.repository.CompanyRepository;
import br.com.omaisfood.service.exception.DataIntegrityException;
import br.com.omaisfood.service.exception.EmailExistsException;
import br.com.omaisfood.service.exception.ErrorCreatingUserException;
import br.com.omaisfood.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired UserService userService;

    CompanyService(CompanyRepository companyRepository, UserService userService){
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    public List<Company> getAllCompanies(){
        return this.companyRepository.findAll();
    }

    public Company saveCompany(Company company, User user) {
        if(this.userService.existsByEmail(user.getEmail()))
            throw new EmailExistsException("Já existe uma conta com este email");

        System.out.println("NAME " + user.getName());
        System.out.println("LASTNAME " + user.getLastName());
        System.out.println("EMAIL " + user.getEmail());
        System.out.println("PASSWORD " + user.getPassword());

        User newUser = this.userService.saveUser(user);

        if(newUser.getId() == null)
            throw new ErrorCreatingUserException("Erro ao criar um usuário e associar a empresa");

        company.setUser(newUser);
        return this.companyRepository.save(company);
    }

    public void deleteCompany(Company company) {
        try {
            this.companyRepository.delete(company);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir a empresa");
        }
    }

    public Company find(Long id){
        Boolean isExists = this.companyRepository.existsById(id);
        if(!isExists){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
        }

        Company company = companyRepository.findById(id).get();
        return company;
    }
}
