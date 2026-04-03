package org.matools.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF (for REST APIs)
                .csrf(AbstractHttpConfigurer::disable)

                // Authorize requests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/actuator/**",   // allow health, metrics
                                "/public/**",     // optional public APIs
                                "/auth/**"        // login/register
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // Optional: HTTP Basic (for testing actuator)
                .httpBasic(Customizer.withDefaults())

                // Optional: JWT filter (if you have one)
                // .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                // Stateless session (important for microservices)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                org.springframework.security.config.http.SessionCreationPolicy.STATELESS
                        )
                );

        return http.build();
    }
}