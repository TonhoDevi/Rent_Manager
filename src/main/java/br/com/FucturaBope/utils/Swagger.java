package br.com.FucturaBope.utils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FucturaBope API")
                        .version("1.0")
                        .description("API para gerenciamento de imóveis, inquilinos e aluguéis."));
    }
}
