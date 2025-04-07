package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Appointment;
import org.example.entity.AppointmentEntity;
import org.example.repository.AppointmentRepository;
import org.example.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class AppointmentServiceImpl implements AppointmentService {

    final AppointmentRepository appointmentRepository;
    final ModelMapper mapper;

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(mapper.map(appointment, AppointmentEntity.class));
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentEntity -> mapper.map(appointmentEntity , Appointment.class))
                .toList();
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
