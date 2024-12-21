package com.filepackage.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;
//Bu sınıfı uygulama başlangıcında tarar ve içindeki @Bean anatasyonlarına sahip metodları yükler
@Configuration
public class WebConfig implements WebMvcConfigurer {

   /* //CORS yapılandırmasını sağlamak için bir WebMvcConfigurer döner
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/ws/**")  // WebSocket endpoint için CORS izinleri
                        .allowedOrigins("http://localhost:8080", "http://localhost:5500", "http://127.0.0.1:5500","http://localhost:3000") // izin verilen origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE"); // izin verilen metodlar
            }
        };
    }*/
    /*  @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Tüm endpointler için
                .allowedOrigins("http://localhost:3000/") // İzin verilen origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // İzin verilen HTTP metodları
                .allowedHeaders("*") // Tüm başlıklar
                .allowCredentials(true); // Kimlik bilgilerini destekler
         }
    }*/

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Tüm endpoint'ler için
                        .allowedOrigins("http://localhost:3000") // Frontend URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // İzin verilen HTTP metodları
                        .allowedHeaders("*") // Tüm header'lar izinli
                        .allowCredentials(true); // Çerezlerin izinli olması için
            }
        };
    }
}