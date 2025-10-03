package com.bastianleond.testprevired.exception.domain;

import com.bastianleond.testprevired.enums.ApiErrorCode;
import com.bastianleond.testprevired.exception.BaseApiException;
import org.springframework.http.HttpStatus;

public class AddressNotFound extends BaseApiException {
    public AddressNotFound(String message) {
        super(message, HttpStatus.CONFLICT, ApiErrorCode.ADDRESS_NOT_FOUND);
    }
}
