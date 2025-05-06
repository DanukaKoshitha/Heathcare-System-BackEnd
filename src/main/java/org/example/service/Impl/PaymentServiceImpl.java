package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Payment;
import org.example.entity.PaymentEntity;
import org.example.repository.PaymentRepository;
import org.example.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper mapper;

    @Override
    public boolean savePayment(Payment payment) {
        PaymentEntity saved = paymentRepository.save(mapper.map(payment, PaymentEntity.class));

        if (saved != null){
            return true;
        }else {
            return false;
        }
    }
}
