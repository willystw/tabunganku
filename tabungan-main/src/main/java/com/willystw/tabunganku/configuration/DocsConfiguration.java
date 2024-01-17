package com.willystw.tabunganku.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocsConfiguration {
  @Bean
  public OpenAPI customOpenAPI() {
    Schema errorSchema = new Schema<Map<String, Object>>()
        .addProperty("errors",
            new ArraySchema()
                .items(new StringSchema())
                .example("[\"str1\", \"str2\", \"str3\"]"));
    return new OpenAPI()
        .components(new Components().addSchemas("ErrorBody", errorSchema));
  }

}
