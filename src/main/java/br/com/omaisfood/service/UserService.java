package br.com.omaisfood.service;

import br.com.omaisfood.model.Address;
import br.com.omaisfood.model.User;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.repository.UserRepository;
import br.com.omaisfood.security.UserSecurity;
import br.com.omaisfood.service.exception.EmailExistsException;
import br.com.omaisfood.service.exception.PermissionDaniedException;
import br.com.omaisfood.service.exception.UserNotFoundException;
import br.com.omaisfood.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    UserService() { }

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User findByEmail(User user) {
        Boolean userByEmail = this.userRepository.existsByEmail(user.getEmail());
        if(!userByEmail) return null;

        return this.userRepository.findByEmail(user.getEmail());
    }

    public User findById(Long id) {
        UserSecurity userLogged = UserService.getUserAuthenticated();
        if(userLogged == null || !userLogged.hasRole(Permission.ADMIN) && !id.equals(userLogged.getId())){
            throw new PermissionDaniedException("Permission Danied");
        }

        Boolean userExists = this.userRepository.existsById(id);
        if(!userExists) {
            throw new UserNotFoundException("User Not Found");
        }

        return this.userRepository.findById(id).get();
    }

    public User getUserLogged() {
        UserSecurity userLogged = UserService.getUserAuthenticated();
        if(userLogged == null){
            throw new UserNotFoundException("User Not Found");
        }

        return this.userRepository.findById(userLogged.getId()).get();
    }

    public User updateUser(User user){
        UserSecurity userLogged = UserService.getUserAuthenticated();
        Boolean userExists = this.userRepository.existsById(user.getId());
        if(userLogged == null || !userExists){
            throw new UserNotFoundException("User Not Found");
        }

        if(!userLogged.hasRole(Permission.ADMIN) && !userLogged.equals(user.getId())){
            throw new PermissionDaniedException("Permission Danied");
        }

        System.out.println("USER ID - " + user.getId());

        this.userRepository.save(user);
        return user;
    }

    public Boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User saveUser(User user){
        if(user.getPermissions().size() == 0)
            user.setPermissions(Permission.ADMIN);

        String passwordEncoded = Utils.cryptPassword(user);
        user.setPassword(passwordEncoded);
        return this.userRepository.save(user);
    }

    public User saveClient(User user){
        Boolean clientExists = this.userRepository.existsByEmail(user.getEmail());
        if(clientExists){
            throw new EmailExistsException("an-account-with-this-email-already-exists");
        }

        user.setPermissions(Permission.CLIENT);
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

    public List<Permission> getUserPermission(Long userId) {
        if(!this.userRepository.existsById(userId)){
            return null;
        }

        User usertoSearch = this.userRepository.findById(userId).get();
        return usertoSearch.getPermissions();
    }

    public static UserSecurity getUserAuthenticated() {
        try{
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            throw new UserNotFoundException("Usuário não encontrado!");
        }
    }
}
