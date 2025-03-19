package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Doctor;
import org.example.repository.DoctorReporsitory;
import org.example.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorReporsitory doctorReporsitory;
    private final ModelMapper mapper;

    @Override
    public void addDoctor(Doctor doctor) {

    }

    @Override
    public List<Doctor> getAll() {
        return List.of();
    }

    @Override
    public Doctor findById(Integer id) {
        return null;
    }

    @Override
    public void updateDoctor(Doctor doctor) {

    }

    @Override
    public void deleteDoctor(Integer id) {

    }
}
