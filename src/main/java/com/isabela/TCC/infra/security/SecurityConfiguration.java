package com.isabela.TCC.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/vagas").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.GET, "/vagas/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.PUT, "/vagas/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.DELETE, "/vagas/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.POST, "/curriculo").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.DELETE, "/curriculo/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.PUT, "/curriculo/**").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.GET, "/curriculo").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.GET, "/**/vagasCandidatadas").hasRole("PROFISSIONAL")
                        .requestMatchers(HttpMethod.GET, "/vagasPostadas/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/profissional").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
