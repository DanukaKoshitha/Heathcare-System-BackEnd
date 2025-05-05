package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/payment")
@RequiredArgsConstructor

public class PaymentController {

    private final PaymentService service;


}
