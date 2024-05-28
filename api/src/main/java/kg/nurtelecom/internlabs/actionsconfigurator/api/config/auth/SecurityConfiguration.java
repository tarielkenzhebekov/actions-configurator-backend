package kg.nurtelecom.internlabs.actionsconfigurator.api.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.ADMINISTRATOR_CREATE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.ADMINISTRATOR_DELETE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.ADMINISTRATOR_READ;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.ADMINISTRATOR_UPDATE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.MANAGER_CREATE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.MANAGER_DELETE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.MANAGER_READ;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.MANAGER_UPDATE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.USER_READ;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Role.ADMINISTRATOR;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Role.MANAGER;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Role.USER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * The SecurityConfiguration class configures security settings for the application.
 * It defines the security filter chain for processing incoming requests and
 * provides method to customize the security configuration.
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {
            "/auth/authenticate",
            "/auth/refresh-token",
            "/auth/authenticate",
            "/auth/logout",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/error"
    };
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    /**
     * This method configures the security filter chain for processing incoming requests.
     * It specifies the filters and their ordering for request processing.
     * @param http The HttpSecurity object to configure the security filter chain.
     * @return The configured security filter chain.
     * @throws Exception If an exception occurs during the security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request
                                .anyRequest().permitAll()
//                                .requestMatchers(WHITE_LIST_URL)
//                                    .permitAll()
//                                .requestMatchers("/auth/register")
//                                    .hasRole(ADMINISTRATOR.name())
//                                .requestMatchers("/auth/register")
//                                    .hasAuthority(ADMINISTRATOR_CREATE.name())
//
//                                .requestMatchers("/user/**")
//                                    .hasRole(ADMINISTRATOR.name())
//                                .requestMatchers(GET, "/user/**")
//                                    .hasAuthority(ADMINISTRATOR_READ.name())
//                                .requestMatchers(POST, "/user/**")
//                                    .hasAuthority(ADMINISTRATOR_CREATE.name())
//                                .requestMatchers(PUT, "/user/**")
//                                    .hasAuthority(ADMINISTRATOR_UPDATE.name())
//                                .requestMatchers(DELETE, "/user/**")
//                                    .hasAuthority(ADMINISTRATOR_DELETE.name())
//
//                                .requestMatchers(GET, "/action/**", "/stage/**", "/rule/**")
//                                    .hasAnyRole(ADMINISTRATOR.name(), MANAGER.name(), USER.name())
//                                .requestMatchers(GET, "/action/**", "/stage/**", "/rule/**")
//                                    .hasAnyAuthority(ADMINISTRATOR_READ.name(), MANAGER_READ.name(), USER_READ.name())
//                                .requestMatchers("/action/**", "/stage/**", "/rule/**", "/statistics/**")
//                                    .hasAnyRole(ADMINISTRATOR.name(), MANAGER.name())
//                                .requestMatchers(POST, "/action/**", "/stage/**", "/rule/**")
//                                    .hasAnyAuthority(ADMINISTRATOR_CREATE.name(), MANAGER_CREATE.name())
//                                .requestMatchers(PUT, "/action/**", "/stage/**", "/rule/**")
//                                    .hasAnyAuthority(ADMINISTRATOR_UPDATE.name(), MANAGER_UPDATE.name())
//                                .requestMatchers(DELETE, "/action/**", "/stage/**", "/rule/**")
//                                    .hasAnyAuthority(ADMINISTRATOR_DELETE.name(), MANAGER_DELETE.name())
//
//                                .anyRequest()
//                                    .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) ->
                                        SecurityContextHolder.clearContext()));
        return http.build();
    }

}