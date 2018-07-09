package br.com.omaisfood.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyEndPoint {

    @GetMapping
    public ResponseEntity<?> getAllCompanies(){
        return new ResponseEntity<>("Olá Mundo!", HttpStatus.OK);
    }
}
