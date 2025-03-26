package org.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Payment {
    private Integer id;

    @NotNull(message = "PaymentMethod not be empty")
    private String paymentType;

    @NotNull(message = "Appointment not be empty")
    private Integer appointmentId;

    @NotNull(message = "Date not be empty")
    private LocalDateTime date;

    @NotNull(message = "Amount not be empty")
    private Double amount;
}
