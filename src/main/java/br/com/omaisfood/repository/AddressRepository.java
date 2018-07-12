package br.com.omaisfood.repository;

import br.com.omaisfood.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    /*
    *  Get addresses by user id
    *  @params User user
    * */
    List<Address> findAddressByUserId(Long user);


}
