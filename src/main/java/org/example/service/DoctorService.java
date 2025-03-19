package org.example.service;

import org.example.dto.Doctor;
import java.util.List;

public interface DoctorService {
    void addDoctor(Doctor doctor);
    List<Doctor> getAll();
    Doctor findById(Integer id);
    void updateDoctor(Doctor doctor);
    void deleteDoctor(Integer id);
}
