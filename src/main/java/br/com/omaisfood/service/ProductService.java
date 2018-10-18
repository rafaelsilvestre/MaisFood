package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.Product;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.repository.ProductRepository;
import br.com.omaisfood.security.UserSecurity;
import br.com.omaisfood.service.exception.BadRequestException;
import br.com.omaisfood.service.exception.InactiveCompanyException;
import br.com.omaisfood.service.exception.PermissionDaniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteProduct(Long productId) {
        UserSecurity userLogged = UserService.getUserAuthenticated();

        // Verify if product exists
        Boolean isExists = this.productRepository.existsById(productId);
        if(!isExists)
            throw new BadRequestException("Product not exists");

        Product product = this.productRepository.findById(productId).get();

        // Verify this user is system admin or company owner
        if(userLogged == null || !userLogged.hasRole(Permission.ADMIN) && !product.getCompany().getUser().getId().equals(userLogged.getId()))
            throw new PermissionDaniedException("Permission danied");

        // Verify if company is inactive
        if(product.getCompany().isInactive())
            throw new InactiveCompanyException("Company is inactive");

        this.productRepository.delete(product);
    }

    public Product getProductById(Long productId) {
        UserSecurity userLogged = UserService.getUserAuthenticated();

        // Verify if product exists
        Boolean isExists = this.productRepository.existsById(productId);
        if(!isExists){
            throw new BadRequestException("Product not exists");
        }

        Product product = this.productRepository.findById(productId).get();

        // Verify this user is system admin or company owner
        if(userLogged == null || !userLogged.hasRole(Permission.ADMIN) && (product.getCompany() != null && !product.getCompany().getUser().getId().equals(userLogged.getId())))
            throw new PermissionDaniedException("Permission danied");

        return product;
    }

    public Product updateProduct(Product newProduct) {
        UserSecurity userLogged = UserService.getUserAuthenticated();

        // Verify if product exists
        Boolean isExists = this.productRepository.existsById(newProduct.getId());
        if(!isExists)
            throw new BadRequestException("Product not exists");

        Product product = this.productRepository.findById(newProduct.getId()).get();

        // Verify this user is system admin or company owner
        if(userLogged == null || !userLogged.hasRole(Permission.ADMIN) && !product.getCompany().getUser().getId().equals(userLogged.getId()))
            throw new PermissionDaniedException("Permission danied");

        // Set current company as owner
        newProduct.setCompany(product.getCompany());

        return this.productRepository.save(newProduct);
    }
}
