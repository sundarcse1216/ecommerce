package com.bzone.ecomm.util;

/**
 * @author sundar
 * @since 16-09-2022
 */
public interface CommonConstance {

    String SUCCESS_CODE = "200";
    String CREATE_SUCCESS = "201";
    String NO_CONTENT = "204";
    String INTERNAL_SERVER_ERROR = "500";

    String NOT_FOUND = "404";

    String BAD_REQUEST = "403";


    //    CORRELATION
    String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    String CORRELATION_ID_LOG_VAR_NAME = "correlationId";
}
