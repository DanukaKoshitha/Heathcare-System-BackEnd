package org.example.SpringSecurity;

import lombok.RequiredArgsConstructor;
import org.example.JWT.JwtAuthenticationFilter;
import org.example.JWT.JwtService.JWTService;
import org.example.repository.DoctorReporsitory;
import org.example.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
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

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class UserConfiguration {

    private final UserRepository userRepository;
    private final DoctorReporsitory doctorRepository;
    private final JWTService jwtService;

    // Shared Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // User Details Services
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    @Bean
    public UserDetailsService doctorDetailsService() {
        return username -> doctorRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Doctor Not Found"));
    }

    // Authentication Providers
    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider doctorAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(doctorDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Unified Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(
                Arrays.asList(
                        userAuthenticationProvider(),
                        doctorAuthenticationProvider()
                )
        );
    }

    // Custom JWT Filter that handles both user types
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, email -> {
            // Try user first, then doctor
            try {
                return userDetailsService().loadUserByUsername(email);
            } catch (UsernameNotFoundException e) {
                return doctorDetailsService().loadUserByUsername(email);
            }
        });
    }

    // Single Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/doctor/login").permitAll()

                        .requestMatchers("/user/**").permitAll()




                        .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/doctor/**").hasAnyAuthority("DOCTOR", "ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}