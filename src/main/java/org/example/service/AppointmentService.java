package org.example.service;

import org.example.dto.Appointment;
import java.util.List;

public interface AppointmentService {

    void addAppointment(Appointment appointment);

    List<Appointment> getAll(Integer userId);

    Appointment searchAppointment(Integer id);

    void updateAppointment(Appointment appointment);

    void deleteAppointment(Integer id);

}
