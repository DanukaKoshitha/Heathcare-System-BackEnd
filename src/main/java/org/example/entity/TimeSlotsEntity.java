package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.DayOfWeek;
import java.time.LocalTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "time_slots")
public class TimeSlotsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean available;
}
