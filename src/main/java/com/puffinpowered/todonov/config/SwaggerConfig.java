package com.puffinpowered.todonov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.puffinpowered.todonov"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(404)
                                .message("Item does not exist")
                                .build()))
                .globalResponseMessage(RequestMethod.PUT, newArrayList(new ResponseMessageBuilder().code(404)
                        .message("Item does not exist")
                        .build()))
                .globalResponseMessage(RequestMethod.DELETE, newArrayList(new ResponseMessageBuilder().code(404)
                        .message("Item does not exist")
                        .build()))
                ;

    }

}
