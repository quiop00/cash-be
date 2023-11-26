package com.ryu.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.ryu.common.sercurity.AuthUserDetail;

@Configuration
public class RedisConfig {
    
    @Autowired
    RedisConnectionFactory factory;

    @Bean
    public RedisTemplate<String, AuthUserDetail> redisTemplateTokenUser() {
        RedisTemplate<String, AuthUserDetail> template = new RedisTemplate<String, AuthUserDetail>();
        template.setConnectionFactory(factory);
        return template;
    }
}
