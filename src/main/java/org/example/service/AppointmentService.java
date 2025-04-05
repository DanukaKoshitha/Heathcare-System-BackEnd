package org.example.service;

import org.example.JWT.AuthenticationResponse;
import org.example.dto.Appointment;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAll();

    Appointment searchAppointment(Integer id);

    void updateAppointment(Appointment appointment);

    void deleteAppointment(Integer id);

    AuthenticationResponse save(Appointment appointment);
}
