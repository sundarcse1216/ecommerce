package com.bzone.ecomm.handler;

import com.bzone.ecomm.dto.BZoneResponse;
import com.bzone.ecomm.exception.RecordNotFoundException;
import com.bzone.ecomm.logger.EcommLogger;
import com.bzone.ecomm.util.CommonConstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sundar
 * @since 17-09-2022
 */
@RestControllerAdvice
public class EcommerceExceptionHandler {

    private final static EcommLogger LOGGER = EcommLogger.getLogger(EcommerceExceptionHandler.class);

    @Autowired
    BZoneResponse response;

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<BZoneResponse> handleEntityNotFoundExceptions(RecordNotFoundException ex) {
        response.setCode(CommonConstance.NOT_FOUND);
        response.setMessage(ex.getMessage());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BZoneResponse> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().stream().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        response.setCode(CommonConstance.BAD_REQUEST);
        response.setMessage("Validation error");
        response.setErrors(errorMap);
        return ResponseEntity.ok(response);
    }

}
