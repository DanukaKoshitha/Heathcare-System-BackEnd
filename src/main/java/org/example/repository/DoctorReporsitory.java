package org.example.repository;

import org.example.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorReporsitory extends JpaRepository<DoctorEntity,Integer> {
}
