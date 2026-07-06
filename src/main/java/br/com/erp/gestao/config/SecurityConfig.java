package br.com.erp.gestao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        // ESTA LINHA É O SEGREDO: ela diz que qualquer um logado passa
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Usa login e senha

        return http.build();
    }
}