package org.example.SpringSecurity;

import lombok.RequiredArgsConstructor;
import org.example.JWT.JwtAuthenticationFilter;
import org.example.JWT.JwtService.JWTService;
import org.example.repository.DoctorReporsitory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class DoctorConfiguration {
    private final DoctorReporsitory doctorRepository;
    private final JWTService jwtService;

    @Bean
    public UserDetailsService doctorDetailsService() {
        return username -> doctorRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Doctor Not Found"));
    }

    @Bean
    @Order(1)
    public SecurityFilterChain doctorSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/doctor/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(doctorAuthenticationProvider())
                .addFilterBefore(doctorJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter doctorJwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, doctorDetailsService());
    }

    @Bean
    public DaoAuthenticationProvider doctorAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(doctorPasswordEncoder());
        provider.setUserDetailsService(doctorDetailsService());
        return provider;
    }

    @Bean
    public PasswordEncoder doctorPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
