package com.springit.workshop.spring.security;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.User;
import com.springit.workshop.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    @Transactional
    protected void doFilterInternal(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
                                    final FilterChain filterChain) throws ServletException, IOException {
        String jwt = parseJwt(httpServletRequest);
        if (jwt != null && JwtUtils.validateJwtToken(jwt)) {
            String email = JwtUtils.getEmailFromJwt(jwt);
            Instant expirationDate = JwtUtils.getIssuedAt(jwt).toInstant();
            User user;

            try {
                user = userService.getUserByEmail(email);
            } catch (SpringBusinessException ex) {
                throw new ServletException("User not found");
            }

            if (isAvailable(expirationDate, user.getLastReset())) {
                SecurityContextHolder.getContext().setAuthentication(user);
                if (expirationDate.isBefore(LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.UTC))) {
                    httpServletResponse.addCookie(JwtUtils.generateJwtCookie(email));
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String parseJwt(HttpServletRequest request) {
        Cookie jwtCookie = WebUtils.getCookie(request, "X-AUTH");
        return jwtCookie != null ? jwtCookie.getValue() : null;
    }

    private boolean isAvailable(Instant issuedAt, Instant lastReset) {
        return lastReset.isBefore(issuedAt);
    }
}