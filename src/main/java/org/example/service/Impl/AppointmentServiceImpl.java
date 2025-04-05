package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.JWT.AuthenticationResponse;
import org.example.JWT.JwtService.JWTService;
import org.example.dto.Appointment;
import org.example.entity.AppointmentEntity;
import org.example.repository.AppointmentRepository;
import org.example.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper mapper;
    private final JWTService jwtService;

    @Override
    public List<Appointment> getAll() {
        return List.of();
    }

    @Override
    public Appointment searchAppointment(Integer id) {
        return null;
    }

    @Override
    public void updateAppointment(Appointment appointment) {

    }

    @Override
    public void deleteAppointment(Integer id) {

    }

    @Override
    public AuthenticationResponse save(Appointment appointment) {
        AppointmentEntity save = appointmentRepository.save(mapper.map(appointment, AppointmentEntity.class));

        String jwtToken = jwtService.generateToken((UserDetails) save);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
