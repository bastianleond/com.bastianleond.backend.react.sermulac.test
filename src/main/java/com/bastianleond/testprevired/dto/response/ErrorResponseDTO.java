package com.bastianleond.testprevired.dto.response;

import com.bastianleond.testprevired.enums.ApiErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorResponseDTO {
    @Builder.Default
    @Schema(description = "Time of the response")
    private OffsetDateTime timestamp = OffsetDateTime.now();
    @Schema(description = "Http Code")
    private int status;
    @Schema(description = "Error Title")
    private String error;
    @Schema(description = "Description")
    private String message;
    @Schema(description = "Requested URL")
    private String path;
    @Schema(description = "Details")
    @JsonProperty("validation_errors")
    private Map<String, String> validationErrors;
    @Schema(description = "Error Code Identifier")
    @JsonProperty("error_code")
    private ApiErrorCode errorCode;
}
