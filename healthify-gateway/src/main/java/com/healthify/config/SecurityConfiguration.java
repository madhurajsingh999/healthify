package com.healthify.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authorization.HttpStatusServerAccessDeniedHandler;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfiguration {

	@Autowired
	private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;
	private final ServerSecurityContextRepository securityContextRepository = new WebSessionServerSecurityContextRepository();
	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;
    private final String[] WHITELIST_URLS={
            "/public/**","/v1/hello",
            "/actuator/**","/swagger-ui/**",
            "/webjars/**","/swagger-ui.html",
            "/challenge/v3/api-docs/**",
            "/reward/v3/api-docs/**",
            "/notification/v3/api-docs/**",
            "/employee/v3/api-docs/**",
            "/auth/v3/api-docs/**",
            "/auth/v1/signin","/auth/v1/signup",
            "/auth/actuator","/auth/actuator/**",
            "/v3/api-docs/**","/","/logout"
    };
	
	
	@Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
         http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                 .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                 .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                 .cors(cors -> cors.configurationSource(request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                        return config;
                }))
                 .exceptionHandling(exceptions -> exceptions
                         .authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED))
                         .accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.FORBIDDEN))
                 )
            .authenticationManager(jwtAuthenticationProvider)
            .securityContextRepository(securityContextRepository)
                 .authorizeExchange(exchanges -> exchanges
                         .pathMatchers(WHITELIST_URLS).permitAll()
                         .pathMatchers(HttpMethod.OPTIONS).permitAll()
                         .anyExchange().authenticated()
                 )
              .addFilterAfter(jwtTokenAuthenticationFilter, SecurityWebFiltersOrder.FIRST);
         
         return http.build();
    }

}