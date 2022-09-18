package com.bzone.ecomm.config;

import com.bzone.ecomm.dto.BZoneResponse;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class EcommConfig {

    @Bean(name = "bzResponse")
    @Primary
//    @Scope(value = "request", proxyMode= ScopedProxyMode.TARGET_CLASS)
//    @RequestScope
    public BZoneResponse bZoneResponse() {
        return new BZoneResponse();
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

}
