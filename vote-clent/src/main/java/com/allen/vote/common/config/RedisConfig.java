package com.allen.vote.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author Allen
 * @version 1.0
 * @description: redis配置
 * @CreateTime -10-27
 * @since 2022/10/27 10:41
 */
@Component
@Slf4j
public class RedisConfig {

    /**
     * 注册一个Redis消息监听器交给Spring管理
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        //Redis消息监听器
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //设置Redis链接工厂
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        // 替换默认序列化
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


}
