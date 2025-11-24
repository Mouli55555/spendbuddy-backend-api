package com.spendbuddy.security;

import com.spendbuddy.security.jwt.AuthEntryPointJwt;
import com.spendbuddy.security.jwt.AuthTokenFilter;
import com.spendbuddy.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;
	private final AuthEntryPointJwt unauthorizedHandler;

	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt unauthorizedHandler) {
		this.userDetailsService = userDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
	}

	@Bean
	public AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// CORS CONFIGURATION - ALLOW ALL ORIGINS
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		// Allow all origins - no restrictions
		configuration.addAllowedOriginPattern("*");

		// Allow all HTTP methods
		configuration.addAllowedMethod("*");

		// Allow all headers
		configuration.addAllowedHeader("*");

		// Allow credentials (important for JWT authentication)
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// Apply this configuration to all paths
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// ENABLE CORS USING THE BEAN DEFINED ABOVE
				.cors(withDefaults())
				.csrf(csrf -> csrf.disable())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/wakeup").permitAll()
						.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/test/**").permitAll()
						.anyRequest().authenticated()
				);

		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
