package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.User;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.repository.CompanyRepository;
import br.com.omaisfood.security.UserSecurity;
import br.com.omaisfood.service.exception.*;
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
        return this.companyRepository.findAllByOrderByIdDesc();
    }

    public Company saveCompany(Company company, User user) {
        if(this.userService.existsByEmail(user.getEmail()))
            throw new EmailExistsException("Já existe uma conta com este email");

        user.setPermissions(Permission.COMPANY);
        User newUser = this.userService.saveUser(user);
        if(newUser.getId() == null)
            throw new ErrorCreatingUserException("Erro ao criar um usuário e associar a empresa");

        company.setUser(newUser);
        return this.companyRepository.save(company);
    }

    public Company saveCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public void deleteCompany(Company company) {
        try {
            this.companyRepository.delete(company);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir a empresa");
        }
    }

    public Company findById(Long id){
        Boolean isExists = this.companyRepository.existsById(id);
        if(!isExists)
            throw new ObjectNotFoundException("Empresa não encontrada");
        return this.companyRepository.findById(id).get();
    }

    public Company getCompanyByUserLogged() {
        UserSecurity userLogged = UserService.getUserAuthenticated();
        User user = this.userService.findById(userLogged.getId());

        if(!userLogged.hasRole(Permission.COMPANY) || user.getCompany().getId() == null)
            throw new BadRequestException("Erro ao buscar empresa");

        return this.companyRepository.findById(user.getCompany().getId()).get();
    }

    public Company updateCompany(Company newCompany, Long companyId) {
        UserSecurity userLogged = UserService.getUserAuthenticated();

        Boolean companyExists = this.companyRepository.existsById(companyId);
        if(!companyExists)
            throw new BadRequestException("Error fetching company");

        Company company = this.companyRepository.findById(companyId).get();

        if(userLogged == null || !userLogged.hasRole(Permission.ADMIN) && !company.getUser().getId().equals(userLogged.getId()))
            throw new PermissionDaniedException("Permission danied");

        company.setName(newCompany.getName());
        company.setDescription(newCompany.getDescription());
        company.setMinimumValue(newCompany.getMinimumValue());

        return this.companyRepository.save(company);
    }

    public Company getCompanyByUserId(Long userId) {
        Boolean companyExists = this.companyRepository.existsByUserId(userId);
        if(!companyExists){
            return null;
        }

        return this.companyRepository.getCompanyByUserId(userId);
    }

    public List<Company> getCompanyByDistrinct(Long distrinctId) {
        return this.companyRepository.findByDistricts_DistrictId(distrinctId);
    }
}
