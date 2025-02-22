package com.quickcontact.quickcontact.config;

import com.quickcontact.quickcontact.filters.JWTAuthFilter;
import com.quickcontact.quickcontact.filters.JwtBlacklistFilter;
import com.quickcontact.quickcontact.services.BlacklistedTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.quickcontact.quickcontact.utils.SecurityUtil.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   BlacklistedTokenService blacklistedTokenService) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(PERMITTED_ALL_PATHS).permitAll()
                .requestMatchers(CUSTOMER_PATHS).authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(new JwtBlacklistFilter(blacklistedTokenService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, JwtBlacklistFilter.class);
        return http.build();
    }

}
