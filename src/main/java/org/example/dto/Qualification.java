package org.example.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qualification {
    @NotBlank
    private String name;

    @Column(nullable = false)
    private String institution;

    @Column(nullable = false)
    private Integer yearObtained;
}
