package br.com.cotiinformatica.api_clientes.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings (CorsRegistry registry) {
        //Permissão para o projeto Angular
        registry.addMapping("/**")//Permissão para todos os ENDPOINTS
                .allowedOrigins("http://localhost:4200")//Aqui é passado o endereço do front-end
                .allowedMethods("POST", "PUT", "DELETE", "GET")//Métodos com permissão de captura e exclusão
                .allowedHeaders("*");
    }
}
