package org.ppke.itk.listen.configuration;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class ListenConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder().dateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
            new Info().title("Listen")
                .version("0.0.1-SNAPSHOT")
                .description("Listen backend")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache2.0").url("http://springdoc.org"))
        );
    }
}