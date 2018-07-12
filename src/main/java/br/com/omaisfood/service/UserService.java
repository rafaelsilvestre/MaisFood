package br.com.omaisfood.service;

import br.com.omaisfood.model.Address;
import br.com.omaisfood.model.User;
import br.com.omaisfood.repository.UserRepository;
import br.com.omaisfood.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    UserService() {

    }

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User saveUser(User user){
        String passwordEncoded = Utils.cryptPassword(user);
        user.setPassword(passwordEncoded);

        return this.userRepository.save(user);
    }

    public User setAddressDefaultForUser(Address address, Long userId){
        Boolean userExists = this.userRepository.existsById(userId);
        if (userExists){
            User user = this.userRepository.findById(userId).get();
            user.setAddressDefault(address);
            this.userRepository.save(user);
            return user;
        }
        return null;
    }
}
