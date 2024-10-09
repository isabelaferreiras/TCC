package com.isabela.TCC.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    public static final List<String> WHITELIST = Arrays.asList(
            "/h2-console/**",
            "/actuator/*",
            "/configuration/security",
            "/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-resources/configuration/security",
            "/swagger-resources/configuration/ui",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/webjars/swagger-ui/**",
            "/error"
    );


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(WHITELIST.toArray(String[]::new)).permitAll()
                       // .requestMatchers(HttpMethod.GET, "/vagas/**").permitAll()
                       // .requestMatchers(HttpMethod.POST, "/curriculo").hasRole("PROFISSIONAL")
                       // .requestMatchers(HttpMethod.DELETE, "/curriculo/**").hasRole("PROFISSIONAL")
                        //.requestMatchers(HttpMethod.PUT, "/curriculo/**").hasRole("PROFISSIONAL")
                       // .requestMatchers(HttpMethod.GET, "/curriculo").hasRole("PROFISSIONAL")
                       // .requestMatchers(HttpMethod.GET, "/curriculo").hasRole("PROFISSIONAL")
                        //.requestMatchers(HttpMethod.GET, "/**/vagasCandidatadas").hasRole("PROFISSIONAL")
                        //.requestMatchers(HttpMethod.GET, "/vagasPostadas/**").hasRole("EMPRESA")
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "profissional/cadastrarProfissional").permitAll()
                        .requestMatchers(HttpMethod.POST, "/empresa/cadastrarEmpresa").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
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
