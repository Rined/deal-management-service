package com.rined.gateway.security.filter;

import com.rined.gateway.security.model.TokenAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class TokenCheckAuthenticationFilter extends GenericFilterBean {
    private static final String HEADER = "Authorization";

    private final AuthenticationManager manager;

    public TokenCheckAuthenticationFilter(AuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authHeader = httpServletRequest.getHeader(HEADER);
        if (Objects.nonNull(authHeader)) {
            Authentication authenticate = manager.authenticate(new TokenAuthentication(authHeader));
            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }
        }
        chain.doFilter(request, response);
    }

}
