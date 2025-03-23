package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Doctor;
import org.example.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")

public class DoctorController {
    final DoctorService service;

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("I DO THAT BRO");
    }

    @PostMapping("/save")
    public void addDoctor(@RequestBody Doctor doctor){
        service.addDoctor(doctor);
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
