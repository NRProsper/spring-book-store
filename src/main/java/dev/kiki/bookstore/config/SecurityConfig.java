package dev.kiki.bookstore.config;

import dev.kiki.bookstore.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //Disabling csrf
                .authorizeHttpRequests(
                        customizer -> customizer
                                //making auth/login and auth/signup routes open to everyone
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                //Allowing unauthenticated users to get all Genres and Books
                                .requestMatchers(HttpMethod.GET, "/api/v1/genres/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/books/**").permitAll()

                                //Only ADMIN can ADD, DELETE AND UPDATE
                                .requestMatchers(HttpMethod.POST, "/api/v1/genres/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/genres/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/genres/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/books/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/books/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/books/**").hasAuthority("ADMIN")
                                
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        customizer -> customizer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
