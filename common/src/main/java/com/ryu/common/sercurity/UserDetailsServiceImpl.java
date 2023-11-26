package com.ryu.common.sercurity;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ryu.common.utils.JsonConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        String json = redisTemplate.opsForValue().get(token);
        return JsonConverter.deserializeObject(json, AuthUserDetail.class);
    }
    
}
