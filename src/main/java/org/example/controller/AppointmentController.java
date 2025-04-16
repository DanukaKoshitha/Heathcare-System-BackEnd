package org.example.controller;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Boolean> addAppointment(@RequestBody Appointment appointment){
        try {
        service.addAppointment(appointment);
        return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(false);
        }
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
    public ResponseEntity<Boolean> deleteAppointment(@RequestParam Integer id){
        service.deleteAppointment(id);
        return ResponseEntity.ok(true);
    }
}
