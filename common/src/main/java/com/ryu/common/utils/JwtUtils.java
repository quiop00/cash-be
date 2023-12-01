package com.ryu.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

@Getter
public class JwtUtils {
    private final Long validSeconds;
    private final Key key;

    public JwtUtils(String key, Long validSeconds) {
        this.validSeconds = validSeconds;
        this.key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            Instant now = Instant.now();
            Date exp = claims.getExpiration();
            return exp.after(Date.from(now));
        } catch (JwtException exception) {
            return false;
        }
    }

    public String encode(String sub) {
        if (StringUtils.isEmpty(sub)) {
            return null;
        }
        Instant exp = Instant.now();

        return Jwts.builder().setSubject(sub).setIssuedAt(new Date(exp.toEpochMilli()))
                .setExpiration(new Date(exp.toEpochMilli() + validSeconds * 1000)).signWith(key).compact();
    }

    public String getSub(String jwt) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
