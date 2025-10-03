package com.bastianleond.testprevired.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AddressDetailsDTO(

        @Schema(description = "This is the unique identifier for address")
        @NotBlank
        UUID uuid,

        @Schema(description = "It is the street name, includes number")
        @NotBlank
        String street,

        @Schema(description = "This represents the commune in chile, like Providencia")
        @NotBlank
        String commune,

        @Schema(description = "This represents the region in chile, like RM")
        @NotBlank
        String region
) {
}
