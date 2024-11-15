package com.example.evotingbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
/**
 * SecurityConfiguation
 */
public class SecurityConfiguation {

        private final JwtAuthentificationFilter jwtAuthFilter;

        private final AuthenticationProvider authenticationProvider;

        private final LogoutHandler logoutHandler;

        @SuppressWarnings("removal")
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http
                                // .csrf().disable().cors().and()
                                .csrf(csrf -> {
                                        try {
                                                csrf
                                                .disable().cors();
                                        } catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                })
                                .authorizeHttpRequests(requests -> requests
                                                .requestMatchers("/api/auth/**")
                                                .permitAll()
                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .logout(logout -> logout
                                                .logoutUrl("/api/auth/logout")
                                                .addLogoutHandler(logoutHandler)
                                                .logoutSuccessHandler((request, response,
                                                                authentication) -> SecurityContextHolder
                                                                                .clearContext()));
                return http.build();
        }

}
