package com.ryu.common.sercurity;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String token) {
        return Optional.ofNullable(token)
                .map(userDetailsService::loadUserByUsername)
                .map(userDetail -> new UsernamePasswordAuthenticationToken(userDetail, userDetail.getPassword(), userDetail.getAuthorities()))
                .orElse(null);
    }
}
