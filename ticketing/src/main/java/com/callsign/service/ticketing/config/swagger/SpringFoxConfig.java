package com.callsign.service.ticketing.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Configuration
public class SpringFoxConfig {
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Automated Ticketing service",
                "backend exercise",
                "API V1",
                "Terms of service",
                new Contact("Junaid Shakeel", "", "junaid.shakeel@live.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
