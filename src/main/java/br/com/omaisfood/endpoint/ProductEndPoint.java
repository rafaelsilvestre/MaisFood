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

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        this.productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{productId}/data")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = this.productService.getProductById(productId);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable Long productId) {
        product.setId(productId);
        Product productUpdate = this.productService.updateProduct(product);
        return new ResponseEntity<Product>(productUpdate, HttpStatus.OK);
    }
}
