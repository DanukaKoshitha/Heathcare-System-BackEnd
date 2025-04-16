package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.JWT.AuthenticationResponse;
import org.example.dto.User;
import org.example.dto.LoginObject;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user){
        return ResponseEntity.ok(service.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginObject loginObject){
        return ResponseEntity.ok(service.login(loginObject));
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
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Integer id){
        service.deleteUser(id);
    }
}
