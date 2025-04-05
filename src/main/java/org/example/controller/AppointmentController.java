package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.JWT.AuthenticationResponse;
import org.example.dto.Appointment;
import org.example.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")

public class AppointmentController {
    private final AppointmentService service;

    @PostMapping("/save")
    public ResponseEntity<AuthenticationResponse> addAppointment(@RequestBody Appointment appointment){
        return ResponseEntity.ok(service.save(appointment));
    }

    @GetMapping("/get-all")
    public List<Appointment> getAll(){
        return service.getAll();
    }

    @GetMapping("/find-by-id")
    public Appointment searchAppointment(@RequestParam Integer id){
        return service.searchAppointment(id);
    }

    @PutMapping("/update")
    public void updateAppointment(@RequestBody Appointment appointment){
        service.updateAppointment(appointment);
    }

    @DeleteMapping("/delete")
    public void deleteAppointment(@RequestParam Integer id){
        service.deleteAppointment(id);
    }
}
