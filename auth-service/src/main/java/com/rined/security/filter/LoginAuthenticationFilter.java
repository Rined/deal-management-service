package com.rined.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rined.security.dto.LoginRequestDto;
import com.rined.security.dto.LoginResponseDto;
import com.rined.security.model.User;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String tokenPrefix = "Bearer ";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto authRequest = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword());
            return this.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new InternalAuthenticationServiceException("Authentication error!", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        final String token = tokenPrefix + user.getUsername();
        LoginResponseDto responseDto = new LoginResponseDto(user.getUsername(), user.getRoles(), token);
        objectMapper.writeValue(response.getWriter(), responseDto);
        response.getWriter().flush();
    }
}
