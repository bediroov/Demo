package az.innakhchivan.config;

import az.innakhchivan.exception.CustomAccessDeniedFilter;
import az.innakhchivan.security.JwtAuthFilter;
import az.innakhchivan.service.UserService;
import lombok.RequiredArgsConstructor;
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


    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        //.requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login", "/api/v1/auth/logout").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/contact", "/api/v1/submit-project", "/api/v1/pdf/upload").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/search",
                                "/api/v1/why-nakhinvest",
                                "/api/v1/why-nakhinvest/*",
                                "/api/v1/who-are-we",
                                "/api/v1/who-are-we/*",
                                "/api/v1/video-gallery/**",
                                "/api/v1/sector",
                                "/api/v1/region",
                                "/api/v1/region/*",
                                "/api/v1/reference/*",
                                "/api/v1/question",
                                "/api/v1/project",
                                "/api/v1/partner-review",
                                "/api/v1/partner-review/*",
                                "/api/v1/news",
                                "/api/v1/map-data",
                                "/api/v1/map-data/*",
                                "/api/v1/map-data/region/*",
                                "/api/v1/incentive",
                                "/api/v1/image/*",
                                "/api/v1/image/all",
                                "/api/v1/how-we-help",
                                "/api/v1/category",
                                "/api/v1/becoming-an-entrepreneur-in-nakhinvest",
                                "/api/v1/about").permitAll()
                        .requestMatchers("/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/who-are-we/all",
                                "/api/v1/why-nakhinvest/all",
                                "/api/v1/sector/all",
                                "/api/v1/region/all",
                                "/api/v1/question/all",
                                "/api/v1/project/all",
                                "/api/v1/partner-review/all",
                                "/api/v1/news/all",
                                "/api/v1/map-data/all",
                                "/api/v1/contact/all",
                                "/api/v1/incentive/all",
                                "/api/v1/how-we-help/all",
                                "/api/v1/category/all",
                                "/api/v1/becoming-an-entrepreneur-in-nakhinvest/all",
                                "/api/v1/about/all",
                                "/api/v1/submit-project/all").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e ->
                        e.accessDeniedHandler(customAccessDeniedFilter)
                )
                .authenticationProvider(authenticationProvider)
                .userDetailsService(userService)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout").addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication)
                                        -> SecurityContextHolder.clearContext())
                )
                .build();
    }
}