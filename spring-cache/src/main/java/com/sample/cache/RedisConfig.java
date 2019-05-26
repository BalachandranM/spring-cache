package com.sample.cache;

import java.time.Duration;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@ComponentScan("com.sample.cache")
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(5);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
		return connectionFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}
	

	@Bean
	public RedisCacheManager redisCacheManager() {
		final RedisCacheWriter redisCacheWriter = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory());
		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		cacheConfiguration.entryTtl(Duration.ofSeconds(30));
		final RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter, cacheConfiguration);
		return redisCacheManager;
	}

	@Bean
	RedisCacheWriter redisCacheWriter() {
		return RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory());
	}

	@Bean
	RedisCacheConfiguration defaultRedisCacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(30));
	}
}