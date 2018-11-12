package br.com.omaisfood.endpoint;

import br.com.omaisfood.dto.UserEditForm;
import br.com.omaisfood.model.User;
import br.com.omaisfood.security.UserSecurity;
import br.com.omaisfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserEndPoint {
    @Autowired
    private UserService userService;

    UserEndPoint(UserService userService){
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(this.userService.saveUser(user), HttpStatus.OK);
    }

    @PostMapping(path = "/new-client")
    public ResponseEntity<User> saveNewClient(@RequestBody User user) {
        User client = this.userService.saveClient(user);
        return new ResponseEntity<User>(client, HttpStatus.OK);
    }

    @GetMapping(path = "/permissions")
    public ResponseEntity<?> getUserPermissions() {
        UserSecurity userLogged = this.userService.getUserAuthenticated();
        return new ResponseEntity<>(this.userService.getUserPermission(userLogged.getId()), HttpStatus.OK);
    }

    @GetMapping(path = "/profile")
    public ResponseEntity<User> getUserProfile() {
        User user = this.userService.getUserLogged();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserFindId(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserEditForm> updateUser(@PathVariable Long id, @RequestBody @Valid UserEditForm userEditForm) {
        User user = User.fromUserEditForm(id, userEditForm);
        this.userService.updateUser(user);
        return new ResponseEntity<UserEditForm>(userEditForm, HttpStatus.OK);
    }
}
