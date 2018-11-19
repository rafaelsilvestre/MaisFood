package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.service.CompanyService;
import br.com.omaisfood.service.StorageService;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("upload")
public class FileUploadEndPoint {

    private final StorageService storageService;

    private final CompanyService companyService;

    @Autowired
    public FileUploadEndPoint(StorageService storageService, CompanyService companyService) {
        this.storageService = storageService;
        this.companyService = companyService;
    }

    @PostMapping(path = "/company/{companyId}")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable Long companyId, HttpServletRequest request) {
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
}