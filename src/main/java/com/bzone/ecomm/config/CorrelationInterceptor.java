package com.bzone.ecomm.config;

import com.bzone.ecomm.logger.EcommLogger;
import com.bzone.ecomm.util.CommonConstance;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationInterceptor extends HttpFilter {

    EcommLogger LOGGER = EcommLogger.getLogger(CorrelationInterceptor.class);

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("CorrelationInterceptor - doFilter - Start");
        String correlationId = getCorrelationIdFromHeader(servletRequest);
        MDC.put(CommonConstance.CORRELATION_ID_LOG_VAR_NAME, correlationId);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            LOGGER.info("Correlation ID removed");
            MDC.remove(CommonConstance.CORRELATION_ID_LOG_VAR_NAME);
        }
        LOGGER.info("CorrelationInterceptor - doFilter - End");
    }

    private String getCorrelationIdFromHeader(final HttpServletRequest request) {
        String correlationId = request.getHeader(CommonConstance.CORRELATION_ID_HEADER_NAME);
        if (StringUtils.isBlank(correlationId)) {
            correlationId = generateUniqueCorrelationId();
//            request.setAttribute(CORRELATION_ID_HEADER_NAME, correlationId);
        }
        return correlationId;
    }

    private String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
