package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.JWT.AuthenticationResponse;
import org.example.JWT.JwtService.JWTService;
import org.example.dto.Doctor;
import org.example.dto.LoginObject;
import org.example.dto.Qualification;
import org.example.dto.TimeSlots;
import org.example.entity.DoctorEntity;
import org.example.entity.QualificationEntity;
import org.example.entity.TimeSlotsEntity;
import org.example.repository.DoctorReporsitory;
import org.example.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .userId(saveDoctor.getId())
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
        var doctor = doctorReporsitory.findByEmail(login.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(doctor);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(doctor.getId())
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
        return mapper.map(doctorReporsitory.findById(id) , Doctor.class);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        DoctorEntity doctorEntity = doctorReporsitory.findById(doctor.getId()).orElseThrow(() -> new RuntimeException("Doctor not found with"+doctor.getId()));

        doctorEntity.setName(doctor.getName());
        doctorEntity.setContact(doctor.getContact());
        doctorEntity.setAddress(doctor.getAddress());
        doctorEntity.setSpecialization(doctor.getSpecialization());

        Type qualificationListType = new TypeToken<List<QualificationEntity>>() {}.getType();
        List<QualificationEntity> qualificationEntities = mapper.map(doctor.getQualifications(), qualificationListType);

        doctorEntity.getQualifications().clear();
        for (QualificationEntity qualification : qualificationEntities) {
            qualification.setDoctor(doctorEntity);
            doctorEntity.getQualifications().add(qualification);
        }

        Type timeSlotListType = new TypeToken<List<TimeSlotsEntity>>() {}.getType();
        List<TimeSlotsEntity> timeSlotEntities = mapper.map(doctor.getTimeSlots(), timeSlotListType);

        doctorEntity.getTimeSlots().clear();
        for (TimeSlotsEntity timeSlot : timeSlotEntities) {
            timeSlot.setDoctor(doctorEntity);
            doctorEntity.getTimeSlots().add(timeSlot);
        }

        doctorEntity.setImage(doctor.getImage());
        doctorEntity.setEmail(doctor.getEmail());
        doctorEntity.setPassword(doctor.getPassword());
        doctorEntity.setYearsOfExperience(doctor.getYearsOfExperience());
        doctorEntity.setPrice(doctor.getPrice());

        DoctorEntity entity = doctorReporsitory.save(doctorEntity);

        return mapper.map(entity, Doctor.class);
    }

    @Override
    public void deleteDoctor(Integer id) {
        doctorReporsitory.deleteById(id);
    }
}
