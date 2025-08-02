//package kh.educat.cstad.aftmobilebankingapi.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
// private  final UserDetailsService userDetailsService;
// private  final PasswordEncoder passwordEncoder;
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider(userDetailsService);
//        daoAuthProvider.setPasswordEncoder(passwordEncoder);
//        return daoAuthProvider;
//
//    }
//
//    @Bean
//    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
//
//
//
////        all requests must be authenticated
//        http.authorizeHttpRequests(requests -> requests
//                .requestMatchers(HttpMethod.POST, "/api/v1/customers/**")
//                .hasAnyRole("ADMIN", "STAFF")
//                .requestMatchers(HttpMethod.GET, "/api/v1/customers/**")
//                .hasAnyRole("ADMIN", "STAFF", "CUSTOMER")
//                .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/**")
//                .hasAnyRole("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/api/v1/customers/**")
//                .hasAnyRole("ADMIN", "STAFF")
//                .requestMatchers(HttpMethod.POST, "/api/v1/accounts/**")
//                .hasAnyRole("USER")
//                .anyRequest().authenticated());
//
//
////        disable form login
//        http.formLogin(form -> form.disable());
//        http.csrf(token -> token.disable());
//
//        http.httpBasic(Customizer.withDefaults());
//
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return http.build();
//
//    }
//}