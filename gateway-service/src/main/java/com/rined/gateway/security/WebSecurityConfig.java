package com.rined.gateway.security;

import com.rined.gateway.security.filter.TokenCheckAuthenticationFilter;
import com.rined.gateway.security.tokens.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTTokenService provider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint())

                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/proposal/**").hasAnyAuthority("ADMIN")

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

    public TokenCheckAuthenticationFilter tokenAuthenticationFilter() throws Exception {
        return new TokenCheckAuthenticationFilter(authenticationManagerBean());
    }
}