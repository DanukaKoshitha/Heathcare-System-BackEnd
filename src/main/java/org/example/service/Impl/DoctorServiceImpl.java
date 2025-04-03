package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.JWT.AuthenticationResponse;
import org.example.JWT.JwtService.JWTService;
import org.example.dto.Doctor;
import org.example.dto.LoginObject;
import org.example.entity.DoctorEntity;
import org.example.repository.DoctorReporsitory;
import org.example.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class DoctorServiceImpl implements DoctorService {

    private final DoctorReporsitory doctorReporsitory;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(Doctor doctor) {
        DoctorEntity doctorEntity = mapper.map(doctor, DoctorEntity.class);
        doctorEntity.setPassword(passwordEncoder.encode(doctor.getPassword()));

        DoctorEntity saveDoctor = doctorReporsitory.save(doctorEntity);

        String jwtToken = jwtService.generateToken(saveDoctor);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginObject login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        var doctor = doctorReporsitory.findByEmail(login.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(doctor);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public List<Doctor> getAll() {
        List<DoctorEntity> all = doctorReporsitory.findAll();

        List<Doctor> doctors = new ArrayList<>();

        all.forEach(doctorEntity -> {
            doctors.add(mapper.map(doctorEntity,Doctor.class));
        });
        return doctors;
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
