package com.isabela.TCC.domain.usuario.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
    private UserAuthenticationFilter userAuthenticationFilter;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/users/login", //url que usaremos para fazer login
            "/users/create" //url que usaremos para criar um usuário
    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/users/test"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de cliente
    public static final String [] ENDPOINTS_PROFISSIONAL = {
            "/users/test/profissional"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de administrador
    public static final String [] ENDPOINTS_EMPRESA = {
            "/users/test/empresa"
    };

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                        .requestMatchers(ENDPOINTS_PROFISSIONAL).hasRole("PROFISSIONAL")
                        .requestMatchers(ENDPOINTS_EMPRESA).hasRole("EMPRESA")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                        .requestMatchers(WHITELIST.toArray(String[]::new)).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
