package com.rined.crud.resolver;

import com.rined.crud.model.AuthenticatedUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@SuppressWarnings("NullableProblems")
public class AuthenticatedUserResolver implements HandlerMethodArgumentResolver {
    private final static String DEFAULT_MESSAGE = "User is not authorized!";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        return new AuthenticatedUser(
                Objects.requireNonNull(webRequest.getHeader("X-UserId"), DEFAULT_MESSAGE),
                Objects.requireNonNull(webRequest.getHeader("X-Username"), DEFAULT_MESSAGE),
                Objects.requireNonNull(webRequest.getHeader("X-UserEmail"), DEFAULT_MESSAGE)
        );
    }
}
