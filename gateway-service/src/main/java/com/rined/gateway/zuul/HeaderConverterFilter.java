package com.rined.gateway.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.rined.gateway.security.model.User;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class HeaderConverterFilter extends ZuulFilter {
    private static final String header = "Authorization";

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String authHeader = request.getHeader(header);
        if (Objects.nonNull(authHeader)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // hystrix если есть ошибки!
            if (authentication.isAuthenticated()) {
                User user = (User) authentication.getPrincipal();
                ctx.addZuulRequestHeader("userId",
                        Objects.requireNonNull(user.getId()));
                ctx.addZuulRequestHeader("username",
                        Objects.requireNonNull(user.getUsername()));
                ctx.addZuulRequestHeader("userEmail",
                        Objects.requireNonNull(user.getEmail()));
            }
        }
        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

}
