package com.rined.template.resolver;

import com.rined.template.model.dto.UserDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@SuppressWarnings("NullableProblems")
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(User.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        return new UserDto(
                Objects.requireNonNull(webRequest.getHeader("userId")),
                Objects.requireNonNull(webRequest.getHeader("username")),
                Objects.requireNonNull(webRequest.getHeader("userEmail"))
        );
    }
}
