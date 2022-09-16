package com.bzone.ecomm.config;

import com.bzone.ecomm.logger.EcommLogger;
import com.bzone.ecomm.util.CommonConstance;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClientCorrelationIdInterceptor implements ClientHttpRequestInterceptor {
    EcommLogger LOGGER = EcommLogger.getLogger(ClientCorrelationIdInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        LOGGER.info("Packing correlation ID in header");
        headers.add(CommonConstance.CORRELATION_ID_HEADER_NAME, MDC.get(CommonConstance.CORRELATION_ID_LOG_VAR_NAME));
        return execution.execute(request, body);
    }
}

