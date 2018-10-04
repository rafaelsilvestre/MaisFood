package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Product;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.security.UserSecurity;
import br.com.omaisfood.service.ProductService;
import br.com.omaisfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductEndPoint {
    @Autowired
    private ProductService productService;

    ProductEndPoint(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{companyId}")
    public ResponseEntity<List<Product>> getAllProductsByCompany(@PathVariable Long companyId) {
        List<Product> products = this.productService.getAllProductsByCompany(companyId);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @PostMapping(path = "/{companyId}")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product, @PathVariable Long companyId) {
        Product newProduct = this.productService.saveProduct(product, companyId);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }


}
