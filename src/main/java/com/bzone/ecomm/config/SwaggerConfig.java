package com.bzone.ecomm.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sundar
 * @since 26-09-2022
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {

        return new OpenAPI()
                .tags(getAllTags())
                .servers(getAllServer())
                .info(new Info().title("Ecommerce API").contact(new Contact().name("Sundar").email("sundarcse1216@gmail.com"))
                        .version(appVersion)
                        .description("Ecommerce API Information - a library for OpenAPI 3 documentation.")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0")
                                .url("http://springdoc.org")));
    }

    private List<Server> getAllServer() {
        List<Server> list = new ArrayList<>();
        list.add(new Server().url("http://localhost:8080").description("Local Server testing"));
        list.add(new Server().url("http://dev:8080").description("Dev Server testing"));
        return list;
    }

    private List<Tag> getAllTags() {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag().name("User").description("The APIs, Contains all the operations that can be performed on a user."));
        tagList.add(new Tag().name("Products").description("Product Module"));
        return tagList;
    }
}
