package com.bastianleond.testprevired.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateAddressRequestDTO(

        @Schema(description = "It is the street name, includes number", example = "Cuadro verde 111")
        @NotBlank(message = "The field street is required")
        String street,

        @Schema(description = "This represents the commune in chile, like Providencia", example = "Estacion Central")
        @NotBlank(message = "The field commune is required")
        String commune,

        @Schema(description = "This represents the region in chile, like RM", example = "RM")
        @NotBlank(message = "The field region is required")
        String region
) {
}
