package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
