package com.bastianleond.testprevired.exception;


import com.bastianleond.testprevired.dto.response.ErrorResponseDTO;
import com.bastianleond.testprevired.enums.ApiErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldNameInCamelCase = error.getField();
            String fieldNameInSnakeCase = camelToSnake(fieldNameInCamelCase);
            errors.put(fieldNameInSnakeCase, error.getDefaultMessage());
        });
        String message = "Validation error, check the fields.";
        log.warn("Request warning error: {}, Path: {}", ex.getMessage(), request.getRequestURI());

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(message)
                .path(request.getRequestURI())
                .validationErrors(errors)
                .errorCode(ApiErrorCode.VALIDATIONS)
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        log.warn("MessageNotReadable: {}, Path: {}", ex.getMessage(), request.getRequestURI());
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("JSON body was bad-formed.")
                .path(request.getRequestURI())
                .errorCode(ApiErrorCode.HTTP_MESSAGE_NOT_READABLE)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoResourceFoundException(NoResourceFoundException ex, HttpServletRequest request) {
        log.warn("NoResourceFoundException: {}, Path: {}", ex.getMessage(), request.getRequestURI());

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message("Path not found")
                .path(request.getRequestURI())
                .errorCode(ApiErrorCode.RESOURCE_NOT_FOUND)
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Unhandled server error. Path: {}", request.getRequestURI(), ex);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Unhandled server error.")
                .path(request.getRequestURI())
                .errorCode(ApiErrorCode.GENERIC)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity<ErrorResponseDTO> handleBaseApiException(BaseApiException ex, HttpServletRequest request) {
        log.warn("Handled Exception: {} - Path: {}", ex.getMessage(), request.getRequestURI());
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .status(ex.getHttpStatus().value())
                .error(ex.getHttpStatus().getReasonPhrase())
                .message(ex.getMessage())
                .errorCode(ex.getApiErrorCode())
                .path(request.getRequestURI())
                .build();


        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    private String camelToSnake(String camelCase) {
        if (camelCase == null) {
            return null;
        }
        return camelCase.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
