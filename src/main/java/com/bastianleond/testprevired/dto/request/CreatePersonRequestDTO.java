package com.bastianleond.testprevired.dto.request;

import com.bastianleond.testprevired.validation.ValidateRut;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

public record CreatePersonRequestDTO(

        @Schema(description = "This is your DNI, without dots and use hyphen", example = "12345678-9")
        @NotBlank(message = "The field rut is required")
        @ValidateRut
        String rut,

        @Schema(description = "This is your first name", example = "Bastian")
        @NotBlank(message = "The field name is required")
        String name,

        @Schema(description = "This is your last name", example = "Leon")
        @NotBlank(message = "The field last_name is required")
        @JsonProperty("last_name")
        String lastName,

        @Schema(description = "Your Birth Date, ex: YYYY-MM-DD", example = "1993-06-04")
        @NotNull(message = "The field birth_date is required")
        @JsonProperty("birth_date")
        LocalDate birthDate,

        @Schema(description = "Address linked to the person")
        @Validated
        CreateAddressRequestDTO address
) {
}
