package org.example.repository;

import org.example.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DoctorReporsitory extends JpaRepository<DoctorEntity,Integer> {

    Optional<DoctorEntity> findByEmail(String email);

}
