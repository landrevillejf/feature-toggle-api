package com.protonmail.landrevillejf.featuretoggle.exception.common;

public class CommonApiException extends RuntimeException {

    public CommonApiException(String message) {
        super(message);
    }

    public CommonApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
