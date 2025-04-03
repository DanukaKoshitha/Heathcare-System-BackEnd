package org.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.Approval;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Appointment {
    private Integer id;

    @NotNull(message = "Doctor Not be empty")
    private Integer doctorId;

    @NotNull(message = "User Not be empty")
    private Integer userId;

    @NotNull(message = "Date not be empty")
    private LocalDateTime date;

//    @NotNull(message = "Payment not be empty")
//    private Integer paymentId;

    @NotNull(message = "Time slot not be empty")
    private String timeSlot;

    @NotNull(message = "Approval not be empty")
    private Approval status;
}


