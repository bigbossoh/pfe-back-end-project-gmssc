package com.bossoh.gmsscbackend.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("CONCEPTION D’UNE APPLICATION DE GESTION DES MAINTENANCES" +
                                        " ET LE SUIVI DU SYSTEME DE CLIMATISATION DE PIGIER CI")
                                .title("GMSSC REST API")
                                .build()
                )
                .groupName("REST API GMSSC V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bossoh.gmsscbackend"))
                .paths(PathSelectors.ant(APP_ROOT+"/**"))
                .build();
    }
}
