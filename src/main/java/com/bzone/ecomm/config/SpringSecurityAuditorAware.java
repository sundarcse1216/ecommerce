package com.bzone.ecomm.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author sundar
 * @since 16-09-2022
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // Just return a string representing the username
        return Optional.ofNullable("Sundar").filter(s -> !s.isEmpty());
    }

}
