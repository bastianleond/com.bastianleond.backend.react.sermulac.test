package com.bastianleond.testprevired.exception.domain;

import com.bastianleond.testprevired.enums.ApiErrorCode;
import com.bastianleond.testprevired.exception.BaseApiException;
import org.springframework.http.HttpStatus;

public class PersonNotFound extends BaseApiException {
    public PersonNotFound(String message) {
        super(message, HttpStatus.CONFLICT, ApiErrorCode.PERSON_NOT_FOUND);
    }
}
