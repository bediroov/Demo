package az.innakhchivan.config;
import az.innakhchivan.exception.CustomAccessDeniedFilter;
import az.innakhchivan.security.JwtAuthFilter;
import az.innakhchivan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final LogoutHandler logoutHandler;
    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;
    private final CustomAccessDeniedFilter customAccessDeniedFilter;


//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedOriginPattern("https://nakhinvest.az");
//        configuration.addAllowedOrigin("http://localhost:4200");
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration); // Apply configuration to all paths
//        return source;
//    }


    private static final String[] WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/**"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Configuring SecurityFilterChain...");

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITELIST).permitAll()

                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers("/api/v1/auth/logout").permitAll()

                        // Use hasRole without 'ROLE_' prefix
                        .requestMatchers("/api/v1/auth/**").hasAnyRole("USER", "ADMIN")

                        // Restricting access to other API endpoints for USER and ADMIN roles
                        .requestMatchers("/api/v1/about/all/**",
                                "/api/v1/becoming-an-entrepreneur-in-nakhinvest/all/**",
                                "/api/v1/contact/all/**",
                                "/api/v1/incentive/all/**",
                                "/api/v1/news/all?**",
                                "/api/v1/partner-feedback/all/**",
                                "/api/v1/image/all/**",
                                "/api/v1/image/download-by-url/**",
                                "/api/v1/project/all/**",
                                "/api/v1/question/all/**",
                                "/api/v1/sector/all/**",
                                "/api/v1/video-gallery/all/**",
                                "/api/v1/why-nakhinvest/all/**",
                                "/api/v1/write-to-us/all/**").hasAuthority("USER")

                        // ADMIN-only access to the remaining endpoints
                        .requestMatchers("/api/v1/**").hasAuthority("ADMIN")

                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e.accessDeniedHandler(customAccessDeniedFilter))
                .authenticationProvider(authenticationProvider)
                .userDetailsService(userService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
                .build();
    }

}
