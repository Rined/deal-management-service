package com.rined.gateway.security;

import com.rined.gateway.security.filter.TokenCheckAuthenticationFilter;
import com.rined.gateway.security.model.Role;
import com.rined.gateway.security.tokens.JWTCheckTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTCheckTokenService provider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfiguration())

                .and()
                .csrf()
                .disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint())

                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/proposals/api/proposals/consumer/**").hasAnyAuthority(Role.CONSUMER.name())
                .antMatchers("/proposals/api/proposals/**").hasAnyAuthority(Role.PROVIDER.name())
                .antMatchers("/templates/api/templates/**").hasAnyAuthority(Role.PROVIDER.name())

                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .rememberMe().disable()
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new TokenAuthenticationProvider(provider));
    }

    private TokenCheckAuthenticationFilter tokenAuthenticationFilter() throws Exception {
        return new TokenCheckAuthenticationFilter(authenticationManagerBean());
    }

    @Bean
    CorsConfigurationSource corsConfiguration() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("OPTIONS");
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}