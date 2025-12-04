package ru.vita.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.vita.service.entity.enums.Role;

import java.util.Set;
import java.util.stream.Collectors;

import static ru.vita.service.entity.enums.Role.*;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/kafka/send", "/login", "/css/**", "/registration", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority(Role.ADMIN.getAuthority())
                        .requestMatchers("/user/**").hasAnyAuthority(USER.getAuthority())
                        .requestMatchers("/worker/**").hasAnyAuthority(WORKER.getAuthority())
                        .requestMatchers("/camper/**").hasAnyAuthority(CAMPER.getAuthority())
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            Set<String> roles = authentication.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toSet());

                            String redirectUrl = "/home";;

                            if (roles.contains(Role.ADMIN.getAuthority())) {redirectUrl = "/admin/home";}
                            if (roles.contains(Role.WORKER.getAuthority())) {redirectUrl = "/worker/home";}
                            if (roles.contains(Role.CAMPER.getAuthority())) {redirectUrl = "/camper/home";}
                            if (roles.contains(Role.USER.getAuthority())) {redirectUrl = "/user/home";}

                            response.sendRedirect(redirectUrl);
                        }));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
