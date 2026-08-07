package com.kharthik.bankingsystem.config;

import com.kharthik.bankingsystem.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/users/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()


                        .requestMatchers("/api/customers/**").hasRole("ADMIN")


                        .requestMatchers(HttpMethod.POST, "/api/accounts").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/accounts/*/deposit")
                        .hasAnyRole("ADMIN", "USER")

                        .requestMatchers(HttpMethod.POST, "/api/accounts/*/withdraw")
                        .hasAnyRole("ADMIN", "USER")

                        .requestMatchers(HttpMethod.POST, "/api/accounts/transfer")
                        .hasAnyRole("ADMIN", "USER")

                        .requestMatchers(HttpMethod.GET, "/api/accounts/*/balance")
                        .hasAnyRole("ADMIN", "USER")

                        .requestMatchers("/api/transactions/**")
                        .hasAnyRole("ADMIN", "USER")

                        .anyRequest().authenticated()


                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





}
