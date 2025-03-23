package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.JWT.AuthenticationResponse;
import org.example.dto.User;
import org.example.dto.UserLogin;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService service;

    @PostMapping("/save")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user){
        return ResponseEntity.ok(service.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserLogin userLogin){
        return ResponseEntity.ok(service.login(userLogin));
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("I DO THAT BRO");
    }

    @GetMapping("/get-all")
    public List<User> getAll(){
        return service.getAll();
    }

    @GetMapping("/search-by-id")
    public User searchUser(@RequestParam Integer id){
        return service.findById(id);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user){
        service.updateUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Integer id){
        service.deleteUser(id);
    }
}
