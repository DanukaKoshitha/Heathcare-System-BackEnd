package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.Approval;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    private String date;

//    @OneToOne
//    @JoinColumn(name = "paymentId",nullable = false,unique = true)
//    private PaymentEntity paymentId;

    @Column(nullable = false)
    private String timeSlot;

    @Column(nullable = false)
    private Approval status;
}
