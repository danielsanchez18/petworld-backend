package com.petworld.petworldbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Sirve archivos estáticos desde la carpeta uploads
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
