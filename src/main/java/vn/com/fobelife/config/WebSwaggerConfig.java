package vn.com.fobelife.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
/**
 * 
 * @author CaNguyen
 *
 */
public class WebSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("vn.com.fobelife"))
                .paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo()).securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("For be life REST API").description(" REST API ")
                .contact(new Contact("hoanganhf1", "www.hoanganhf1.vn", "hoanganhf1@gmail.com")).license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0").build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[] { authorizationScope };
        return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

}
