package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Appointment;
import org.example.dto.User;
import org.example.entity.AppointmentEntity;
import org.example.repository.AppointmentRepository;
import org.example.service.AppointmentService;
import org.example.util.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class AppointmentServiceImpl implements AppointmentService {

    final AppointmentRepository appointmentRepository;
    final ModelMapper mapper;
    final UserServiceImpl userService;

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(mapper.map(appointment, AppointmentEntity.class));
    }

    @Override
    public List<Appointment> getAll(Integer userId) {
        return appointmentRepository.findByUser_Id(userId)
                .stream()
                .map(entity -> mapper.map(entity, Appointment.class))
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
        appointmentRepository.deleteById(id);
    }

    @Override
    public void updateAppointmentStatus(Integer appointmentId, String status) {
        User user = userService.findById(appointmentId);
        user.setRole(UserRole.valueOf(status));
    }

    @Override
    public List<Appointment> getAllAppoitmentsForAdmin() {
        return appointmentRepository.findAll()
                .stream().map(appointmentEntity -> mapper.map(appointmentEntity, Appointment.class))
                .toList();
    }
}
