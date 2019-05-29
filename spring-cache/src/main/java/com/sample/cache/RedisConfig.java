package com.sample.cache;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
		poolConfig.setMaxTotal(6);
		poolConfig.setMinIdle(2);
		poolConfig.setMaxIdle(5);
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
		// redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

	@Bean
	public RedisCacheConfiguration defaultRedisCacheConfiguration() {
		SerializationPair<Object> valueSerializationPair = RedisSerializationContext.SerializationPair
				.fromSerializer(new GenericJackson2JsonRedisSerializer());
		RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		defaultCacheConfiguration = defaultCacheConfiguration.serializeValuesWith(valueSerializationPair);
		defaultCacheConfiguration = defaultCacheConfiguration.entryTtl(Duration.ofSeconds(30));
		return defaultCacheConfiguration;
	}

	@Bean
	public CacheManager cacheManager() {
		Map<String, RedisCacheConfiguration> cacheNamesConfigurationMap = new HashMap<>();
		SerializationPair<Object> valueSerializationPair = RedisSerializationContext.SerializationPair
				.fromSerializer(new GenericJackson2JsonRedisSerializer());
		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		cacheConfiguration = cacheConfiguration.serializeValuesWith(valueSerializationPair);
		cacheConfiguration = cacheConfiguration.entryTtl(Duration.ofSeconds(20));
		cacheNamesConfigurationMap.put("STUDENT", cacheConfiguration);
		return new RedisCacheManager(redisCacheWriter(), defaultRedisCacheConfiguration(), cacheNamesConfigurationMap);
	}

	@Bean
	RedisCacheWriter redisCacheWriter() {
		return RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory());
	}

}