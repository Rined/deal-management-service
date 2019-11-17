package com.rined.security.config;

import com.rined.security.filter.LoginAuthenticationFilter;
import com.rined.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService service;

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
                .formLogin().disable()
                .httpBasic().disable()
                .rememberMe().disable()
                .addFilterBefore(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                .logout()
                .logoutUrl("/exit")

                .and()
                .headers().frameOptions().disable();    // fix: in a frame because it set 'X-Frame-Options' to 'deny'.;;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    public UsernamePasswordAuthenticationFilter loginAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter authenticationFilter = new LoginAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(
                (req, resp, exc) -> resp.setStatus(HttpStatus.OK.value()));
        authenticationFilter.setAuthenticationFailureHandler(
                new SimpleUrlAuthenticationFailureHandler());
        authenticationFilter.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }
}