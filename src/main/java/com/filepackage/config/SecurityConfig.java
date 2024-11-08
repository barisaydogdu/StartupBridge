package com.filepackage.config;

import com.filepackage.Exception.CustomExceptionHandler;
import com.filepackage.filter.JwtAuthenticationFilter;
import com.filepackage.service.impl.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailServiceImpl userDetailService;
    private JwtAuthenticationFilter authenticationFilter;
    @Autowired
    private CustomExceptionHandler accessDeniedHandler;
    public SecurityConfig(UserDetailServiceImpl userDetailService, JwtAuthenticationFilter authenticationFilter) {
        this.userDetailService = userDetailService;
        this.authenticationFilter = authenticationFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/login/**","/register/**","/ws/**","/api/users/**")
                                .permitAll()
                                .requestMatchers("/admin_only/**","/app/**").hasAnyAuthority("ROLE_ADMIN")
                                .anyRequest()
                                .authenticated()//diğer tüm istekler için doğrulama gerekir
                )
                .userDetailsService(userDetailService)
                .sessionManagement(session->session //Oturum yönetimi yapılandırması başlatılır
                        //Sunucu istemci hakkında herhangi bir oturum bilgisi saklamaz
                        //Oturum yönetimi yapılandırılması başlatılır.
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //jwt tokenları filtreden geçirir ve filtre zincirine ekler.
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
