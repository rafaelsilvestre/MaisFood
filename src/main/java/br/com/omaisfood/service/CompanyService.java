package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.repository.CompanyRepository;
import br.com.omaisfood.service.exception.DataIntegrityException;
import br.com.omaisfood.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies(){
        return this.companyRepository.findAll();
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

    public Company find(Long id){
        Boolean isExists = this.companyRepository.existsById(id);
        if(!isExists){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
        }

        Company company = companyRepository.findById(id).get();
        return company;
    }
}
