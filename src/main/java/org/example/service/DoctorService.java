package org.example.service;

import org.example.JWT.AuthenticationResponse;
import org.example.dto.Doctor;
import org.example.dto.LoginObject;
import java.util.List;

public interface DoctorService {

    AuthenticationResponse register(Doctor doctor);

    AuthenticationResponse login(LoginObject login);

    List<Doctor> getAll();

    Doctor findById(Integer id);

    Doctor updateDoctor(Doctor doctor);

    void deleteDoctor(Integer id);
}
