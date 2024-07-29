package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

@Configuration
public class SwaggerConfiguration {
    private static final String API_VERSION = "1.0.0";
    private static final String API_TITLE = "Demo API";
    private static final String API_DESCRIPTION = "Demo API Description";

    @Bean
    public OpenAPI openAPI() {
        final Info apiInformation = getApiInformation();
        final Components components = new Components();

        final String schemeName = "bearerAuth";
        components.addSecuritySchemes(schemeName, new SecurityScheme().name(schemeName).type(HTTP).scheme("Bearer").bearerFormat("JWT"));

        final OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(apiInformation);
        openAPI.setComponents(components);
        openAPI.addSecurityItem(new SecurityRequirement().addList(schemeName));

        return openAPI;
    }

    private Info getApiInformation() {
        final Info info = new Info();
        info.setTitle(API_TITLE);
        info.setVersion(API_VERSION);
        info.setDescription(API_DESCRIPTION);

        return info;
    }

    @Bean
    GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder().pathsToExclude("/actuator/**").group("Default Api").build();
    }
}
