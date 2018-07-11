package br.com.omaisfood.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("auth")
public class AuthEndPoint {

    @PostMapping
    public ResponseEntity<HashMap<String, String>> authUser(){
        HashMap<String, String> map = new HashMap<>();
        map.put("token", "sad897dsfa89fsda8sdfa789dfsa8790sdfa9");
        return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.OK);
    }
}
