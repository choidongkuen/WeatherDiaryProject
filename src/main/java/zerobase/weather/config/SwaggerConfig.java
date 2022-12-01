package zerobase.weather.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig{

    @Bean
    public OpenAPI apiInfo(){
        return new OpenAPI().info(new Info().title("날씨 일기 프로젝트 :)"));
    }

    @Bean
    public GroupedOpenApi httpApi(){
        return GroupedOpenApi.builder()
                .displayName("입력해주세요")
                .group("http")
                .pathsToMatch("/**")
                .build();
    }
}