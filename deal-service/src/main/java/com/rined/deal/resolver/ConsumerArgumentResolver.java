package com.rined.deal.resolver;

import com.rined.deal.model.dto.ConsumerDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@SuppressWarnings("NullableProblems")
public class ConsumerArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(Consumer.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        return new ConsumerDto(
                Objects.requireNonNull(webRequest.getHeader("userId")),
                Objects.requireNonNull(webRequest.getHeader("username")),
                Objects.requireNonNull(webRequest.getHeader("userEmail"))
        );
    }
}
