package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.User;
import br.com.omaisfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserEndPoint {

    @Autowired
    private UserService userService;

    UserEndPoint(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.OK);
    }
}
