package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.JWT.AuthenticationResponse;
import org.example.dto.Doctor;
import org.example.dto.LoginObject;
import org.example.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")

public class DoctorController {
    final DoctorService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> addDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(service.register(doctor));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginDoctor(@RequestBody LoginObject login){
        return ResponseEntity.ok(service.login(login));
    }

    @GetMapping("/get-all")
    public List<Doctor> getAll(){
        return service.getAll();
    }

    @GetMapping("/search-by-id")
    public Doctor searchDoctor(@RequestParam Integer id){
        return service.findById(id);
    }

    @PutMapping("/update")
    public void updateDoctor(@RequestBody Doctor doctor){
        service.updateDoctor(doctor);
    }

    @DeleteMapping("/delete")
    public void deleteDoctor(@RequestParam Integer id){
        service.deleteDoctor(id);
    }
}
