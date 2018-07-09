package br.com.omaisfood.service;

import br.com.omaisfood.model.Address;
import br.com.omaisfood.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address){
        return this.addressRepository.save(address);
    }
}
