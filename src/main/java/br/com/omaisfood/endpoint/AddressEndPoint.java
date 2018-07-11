package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Address;
import br.com.omaisfood.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressEndPoint {
    @Autowired
    private AddressRepository addressRepository;

    AddressEndPoint(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies(){
        return new ResponseEntity<>(this.addressRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveAddress(@RequestBody Address address) {
        return new ResponseEntity<>(this.addressRepository.save(address), HttpStatus.OK);
    }
}
