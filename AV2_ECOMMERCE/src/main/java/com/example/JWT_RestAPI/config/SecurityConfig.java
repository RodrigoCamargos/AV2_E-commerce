package com.example.JWT_RestAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/username/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/gerentedeprodutos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vendendor/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cliente/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/gerentedeprodutos/**").hasRole("GERENTEDEPRODUTOS")
                        .requestMatchers("/vendedor/**").hasRole("VENDEDOR")
                        .requestMatchers("/cliente/**").hasRole("CLIENTE")
                        .anyRequest()
                        .authenticated()
                ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("Larissa")
                .password(passwordEncoder().encode("4321"))
                .roles("ADMIN")
                .build();
        UserDetails gerentedeprodutos = User.builder()
                .username("Chandler")
                .password(passwordEncoder().encode("123"))
                .roles( "GERENTEDEPRODUTOS")
                .build();
        UserDetails vendedor = User.builder()
                .username("Monica")
                .password(passwordEncoder().encode("123"))
                .roles( "VENDEDOR")
                .build();
        UserDetails cliente = User.builder()
                .username("Phebs")
                .password(passwordEncoder().encode("123"))
                .roles( "CLIENTE")
                .build();
        return new InMemoryUserDetailsManager(user, gerentedeprodutos, vendedor,cliente);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
