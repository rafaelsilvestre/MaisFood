package br.com.omaisfood.service;

import br.com.omaisfood.model.Address;
import br.com.omaisfood.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    AddressService() {

    }

    AddressService(AddressRepository addressRepository, UserService userService){
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    public List<Address> getAllAddress(){
        return this.addressRepository.findAll();
    }

    public Address saveAddress(Address addr){
        List<Address> currentUserAddresses = this.addressRepository.findAddressByUserId(addr.getUser().getId());
        Address address = this.addressRepository.save(addr);
        if(currentUserAddresses.size() == 0){
            this.setAddressDefaultForUser(address.getId(), addr.getId());
        }
        return address;
    }

    public Address setAddressDefaultForUser(Long addressId, Long userId){
        Boolean addressExists = this.addressRepository.existsById(addressId);
        if (addressExists){
            Address address = this.addressRepository.findById(addressId).get();
            this.userService.setAddressDefaultForUser(address, userId);
            return address;
        }
        return null;
    }
}
