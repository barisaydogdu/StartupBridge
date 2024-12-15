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
   /* @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**")  // Tüm endpointler için
               .allowedOrigins("http://localhost:3000")  // Sondaki slash'i kaldırdık
               .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
               .allowedHeaders("*")
               .exposedHeaders("*")  // Bu satırı ekledik
               .allowCredentials(true)
               .maxAge(3600);  // Preflight cache süresi


    }*/


        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .exposedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
        }

    }
