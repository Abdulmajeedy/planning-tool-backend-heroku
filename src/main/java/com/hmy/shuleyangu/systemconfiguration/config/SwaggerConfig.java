package com.hmy.shuleyangu.systemconfiguration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        ApiInfoBuilder builder = new ApiInfoBuilder().title("Shuleyangu SystemConfig API Documentation")
                .description("Documentation automatically generated").version("1.0.0")
                .contact(new Contact("HM&Y Technologies Developers", "hmytechnologies.com", "hmy@hmytechnologies.com"));
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.hmy.shuleyangu.systemconfiguration"))
                .paths(PathSelectors.any()).build()
                //.securitySchemes(Arrays.asList(securityScheme()))
                //.securityContexts(Arrays.asList(securityContext()))
                .apiInfo(builder.build());
    }

}