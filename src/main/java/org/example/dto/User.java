package org.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.GenderType;
import org.example.util.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer id;

    @NotNull(message = "FirstName not be empty")
    private String firstName;

    @NotNull(message = "LastName not be empty")
    private String lastName;

    @NotNull(message = "Contact not be empty")
    private String contact;

    @NotNull(message = "Address not be empty")
    private String address;

    @NotNull(message = "Role not be empty")
    private UserRole role;

    @NotNull(message = "Gender not be empty")
    private GenderType gender;

    @NotNull(message = "Massage not be empty")
    private String email;

    @NotNull(message = "Password not be empty")
    private String password;
}
