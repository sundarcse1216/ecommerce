package com.bzone.ecomm;

import com.bzone.ecomm.logger.EcommLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
//@OpenAPIDefinition(info = @Info(title = "Ecommerce API", version = "1.0", description = "Ecommerce Information"))
public class EcommApplication {
    private final static EcommLogger LOGGER = EcommLogger.getLogger(EcommApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EcommApplication.class, args);
        LOGGER.info("Ecomm Service started successfully...");
    }
}
