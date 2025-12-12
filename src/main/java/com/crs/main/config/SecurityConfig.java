package com.crs.main.config;

import com.crs.main.security.JwtAccessDeniedHandler;
import com.crs.main.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private JwtAuthFilter jwtAuthFilter;

    public SecurityConfig() {
    }

    @Autowired
    public SecurityConfig(JwtAccessDeniedHandler jwtAccessDeniedHandler,
                          JwtAuthFilter jwtAuthFilter) {
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    private static final String[] PUBLIC_URLS = {
            "/auth/**",
            "/cars/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling(ex -> ex.accessDeniedHandler(jwtAccessDeniedHandler) // 403
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/cars/**").hasRole("USER")
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}