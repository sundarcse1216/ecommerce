package com.bzone.ecomm;

import com.bzone.ecomm.logger.EcommLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommApplication {
    private final static EcommLogger LOGGER = EcommLogger.getLogger(EcommApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EcommApplication.class, args);
        LOGGER.info("Ecomm Service started successfully...");
    }
}
