package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Appointment;
import org.example.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AppointmentServiceImpl implements AppointmentService {
    @Override
    public void addAppointment(Appointment appointment) {

    }

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
}
