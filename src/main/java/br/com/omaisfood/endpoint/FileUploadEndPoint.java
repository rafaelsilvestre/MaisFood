package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.Product;
import br.com.omaisfood.service.CompanyService;
import br.com.omaisfood.service.ProductService;
import br.com.omaisfood.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("upload")
public class FileUploadEndPoint {

    private final StorageService storageService;

    private final CompanyService companyService;

    private final ProductService productService;

    @Autowired
    public FileUploadEndPoint(StorageService storageService, CompanyService companyService, ProductService productService) {
        this.storageService = storageService;
        this.companyService = companyService;
        this.productService = productService;
    }

    @PostMapping(path = "/company/{companyId}")
    public ResponseEntity<?> handleFileUploadByCompany(@RequestParam("file") MultipartFile file, @PathVariable Long companyId, HttpServletRequest request) {
        String filename = storageService.store(file);
        Company company = this.companyService.findById(companyId);

        String namefile;
        if(request.getServerPort() != 80) {
            namefile = String.format("%s://%s:%s/upload/company/%s", (request.isSecure() ? "https" : "http"), request.getServerName(), request.getServerPort(), filename);
        } else {
            namefile = String.format("%s://%s/upload/company/%s", (request.isSecure() ? "https" : "http"), request.getServerName(), filename);
        }

        // Save company image
        company.setImage(namefile);
        this.companyService.saveCompany(company);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/product/{productId}")
    public ResponseEntity<?> handleFileUploadByProduct(@RequestParam("file") MultipartFile file, @PathVariable Long productId, HttpServletRequest request) {
        String filename = storageService.store(file);
        Product product = this.productService.getProductById(productId);

        String namefile;
        if(request.getServerPort() != 80) {
            namefile = String.format("%s://%s:%s/upload/product/%s", (request.isSecure() ? "https" : "http"), request.getServerName(), request.getServerPort(), filename);
        } else {
            namefile = String.format("%s://%s/upload/product/%s", (request.isSecure() ? "https" : "http"), request.getServerName(), filename);
        }

        // Save product image
        product.setImage(namefile);
        this.productService.saveProduct(product, product.getCompany().getId());

        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @GetMapping("/company/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, HttpServletRequest request) {
        Resource file = storageService.loadAsResource(filename);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        } catch (IOException ex) { }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @ResponseBody
    @GetMapping("/product/{filename:.+}")
    public ResponseEntity<Resource> serveFileByProduct(@PathVariable String filename, HttpServletRequest request) {
        Resource file = storageService.loadAsResource(filename);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        } catch (IOException ex) { }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}