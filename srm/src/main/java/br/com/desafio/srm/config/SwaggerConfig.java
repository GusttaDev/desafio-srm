package br.com.desafio.srm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(List.of(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.desafio.srm.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API Desafio SRM")
                .description("API responsável por cadastro de clientes")
                .version("1.0.0")
                .build();
    }

    SecurityContext securityContext(){
        return SecurityContext.builder()
                .forPaths(PathSelectors.any())
                .build();
    }
}
