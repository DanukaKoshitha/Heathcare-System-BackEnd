package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService service;

    @PostMapping("/save")
    public void addUser(@RequestBody User user){
        service.addUser(user);
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
