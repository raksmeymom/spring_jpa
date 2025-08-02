package kh.educat.cstad.aftmobilebankingapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class KeycloakSecurityConfig {
    private final String ROLE_ADMIN = "ROLE_ADMIN";
    private final String ROLE_STAFF = "ROLE_STAFF";
    private final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.POST, "/api/v1/customers/**")
                .hasAnyRole("ADMIN", "STAFF")
                .requestMatchers(HttpMethod.GET, "/api/v1/customers/**")
                .hasAnyRole("ADMIN", "STAFF", "CUSTOMER")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/**")
                .hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/customers/**")
                .hasAnyRole("ADMIN", "STAFF")
                .requestMatchers(HttpMethod.POST, "/api/v1/accounts/**")
                .hasAnyRole("USER")
                .anyRequest().authenticated());

        http.formLogin(form -> form.disable());
        http.csrf(token -> token.disable());

        //SET security mechanism = OAUTH2
        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(Customizer.withDefaults()));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            if (realmAccess == null || realmAccess.get("roles") == null) {
                return Collections.emptyList();
            }

            Collection<String> roles = (Collection<String>) realmAccess.get("roles");
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
        };

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
}