package com.ryu.common.sercurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ryu.common.utils.JwtUtils;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	public static final String TOKEN_PREFIX = "Bearer ";
    private final JwtUtils jwtUtils;
    private final AuthenticationProvider authenticationProvider;

	 @Override
	    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
	        Optional.ofNullable(((HttpServletRequest)request).getHeader(HttpHeaders.AUTHORIZATION))
	                .filter(authHeader -> authHeader.startsWith(TOKEN_PREFIX))
	                .map(authHeader -> authHeader.substring(TOKEN_PREFIX.length()))
	                .filter(jwtUtils::validateToken)
	                .map(authenticationProvider::getAuthentication)
	                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
	        filterChain.doFilter(request, response);
	    }
}
