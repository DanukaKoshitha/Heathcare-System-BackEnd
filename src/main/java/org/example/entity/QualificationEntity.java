package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qualifications")

public class QualificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String institution;

    @Column(nullable = false)
    private Integer yearObtained;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    //  Automatically assign doctor when adding qualification
    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
        if (doctor != null && !doctor.getQualifications().contains(this)) {
            doctor.getQualifications().add(this);
        }
    }
}
