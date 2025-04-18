package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Appointment")

public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctorId",nullable = false)
    private DoctorEntity doctorId;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserEntity userId;

    @Column(nullable = false)
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "paymentId",nullable = false,unique = true)
    private PaymentEntity paymentId;
}
