package com.example.configuration.openApi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class OpenApiConfiguration {

    private final OpenApiPropertiesConfiguration environment;


    @Bean
    public OpenAPI customOpenAPI() {

          return new OpenAPI()
                  .components(new Components())
                  .info(new Info()
                  .title("Produtos")
                  .description("API responsável por gerenciar as informações de produtos")
                  .version(environment.getAppVersion()))
                  .tags(List.of(new Tag().name("Produtos").description("Endpoints responsáveis por gerenciar as informações de produtos")
                  )
          );
    }
}