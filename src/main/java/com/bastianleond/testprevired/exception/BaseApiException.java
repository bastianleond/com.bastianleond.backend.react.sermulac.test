package com.bastianleond.testprevired.exception;

import com.bastianleond.testprevired.enums.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BaseApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ApiErrorCode apiErrorCode;

    protected BaseApiException(String message, HttpStatus httpStatus, ApiErrorCode apiErrorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.apiErrorCode = apiErrorCode;
    }
}
