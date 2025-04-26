package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Doctor {

    private Integer id;

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Contact Number cannot be empty")
    private String contact;

    @NotNull(message = "Address cannot be empty")
    private String address;

    @NotNull(message = "Specialization cannot be empty")
    private String specialization;

    @NotEmpty(message = "At least one qualification must be specified")
    private List<Qualification> qualifications;

    @NotEmpty(message = "At least one time slot must be specified")
    private List<TimeSlots> timeSlots;

    @NotNull(message = "Image URL cannot be empty")
    private String image;

    @NotNull(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Password cannot be empty")
    //@JsonIgnore // Password not send to FrontEnd
    private String password;

    @NotNull(message = "Experience cannot be empty")
    private Integer yearsOfExperience;

    @NotNull(message = "Appointment price cannot be empty")
    private Double price;
}
