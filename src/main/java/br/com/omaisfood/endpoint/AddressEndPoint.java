package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Address;
import br.com.omaisfood.repository.AddressRepository;
import br.com.omaisfood.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressEndPoint {
    @Autowired
    private AddressService addressService;

    AddressEndPoint(AddressRepository addressRepository){
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddress(){
        return new ResponseEntity<List<Address>>(this.addressService.getAllAddress(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        return new ResponseEntity<Address>(this.addressService.saveAddress(address), HttpStatus.OK);
    }

    @PostMapping(path = "/{addressId}/user/{userId}")
    public ResponseEntity<Address> setAddressDefaultForUser(@PathVariable Long addressId, @PathVariable Long userId){
        Address address = this.addressService.setAddressDefaultForUser(addressId, userId);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }
}
