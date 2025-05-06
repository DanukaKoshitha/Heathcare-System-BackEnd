package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Payment;
import org.example.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/payment")
@RequiredArgsConstructor

public class PaymentController {

    private final PaymentService service;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createPayment(@RequestBody Payment payment){
        if (service.savePayment(payment)){
            return ResponseEntity.ok(true);
        }else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
