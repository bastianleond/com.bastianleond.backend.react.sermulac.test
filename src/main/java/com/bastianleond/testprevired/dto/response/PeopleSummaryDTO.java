package com.bastianleond.testprevired.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record PeopleSummaryDTO(

        @Schema(description = "This is the unique identifier for person")
        @NotBlank
        UUID uuid,

        @Schema(description = "This is your DNI, without dots and use hyphen", example = "12345678-9")
        @NotBlank
        String rut,

        @Schema(description = "This is your first name", example = "Bastian")
        @NotBlank
        String name,

        @Schema(description = "This is your last name", example = "Leon")
        @NotBlank
        @JsonProperty("last_name")
        String lastName,

        @Schema(description = "Your Birth Date, ex: YYYY-MM-DD", example = "1993-06-04")
        @NotNull
        @JsonProperty("birth_date")
        LocalDate birthDate,

        @Schema(description = "This is the age of the person")
        @NotNull
        Integer age
) {
}
