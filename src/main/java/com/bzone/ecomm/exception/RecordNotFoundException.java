package com.bzone.ecomm.exception;

/**
 * @author sundar
 * @since 17-09-2022
 */
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
