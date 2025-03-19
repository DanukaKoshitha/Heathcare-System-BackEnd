package org.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Doctor {
    private Integer id;

    @NotNull(message = "Name not be empty")
    private String name;

    @NotNull(message = "Contact Number not be empty")
    private String contact;

    @NotNull(message = "Address not be empty")
    private String address;

    @NotNull(message = "Subject not be empty")
    private String subject;

    @NotNull(message = "Qualifications not be empty")
    private String qualification;

    @NotNull(message = "Image not be empty")
    private String image;

    @NotNull(message = "Email not be empty")
    private String email;

    @NotNull(message = "Password not be empty")
    private String password;
}
