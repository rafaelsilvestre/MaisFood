package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.Product;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.repository.ProductRepository;
import br.com.omaisfood.security.UserSecurity;
import br.com.omaisfood.service.exception.InactiveCompanyException;
import br.com.omaisfood.service.exception.PermissionDaniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.PortableRemoteObjectDelegate;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyService companyService;

    public List<Product> getAllProductsByCompany(Long companyId) {
        UserSecurity userLogged = UserService.getUserAuthenticated();
        Company company = this.companyService.findById(companyId);

        // Verify this user is system admin or company owner
        if(userLogged == null || !userLogged.hasRole(Permission.ADMIN) && !company.getUser().getId().equals(userLogged.getId()))
            throw new PermissionDaniedException("Permission danied");

        return this.productRepository.findAll();
    }

    public Product saveProduct(Product product, Long companyId) {
        UserSecurity userLogged = UserService.getUserAuthenticated();
        Company company = this.companyService.findById(companyId);

        // Verify this user is system admin or company owner
        if(userLogged == null || !userLogged.hasRole(Permission.ADMIN) && !company.getUser().getId().equals(userLogged.getId()))
            throw new PermissionDaniedException("Permission danied");

        // Verify if company is inactive
        if(company.isInactive())
            throw new InactiveCompanyException("Company is inactive");

        // Define product owner
        product.setCompany(company);

        return this.productRepository.save(product);
    }
}
