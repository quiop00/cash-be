package com.ryu.common.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ryu.common.repository.UserRepository;
import com.ryu.common.utils.JsonConverter;
import com.ryu.common.utils.JwtUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        try {
            String json = redisTemplate.opsForValue().get(token);
            return JsonConverter.deserializeObject(json, AuthUserDetail.class);
        } catch (Exception e) {
            String email = jwtUtils.getSub(token);
            return userRepository.findByEmail(email).map(user -> AuthUserDetail.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build())
                    .orElse(null);
        }

    }

}
