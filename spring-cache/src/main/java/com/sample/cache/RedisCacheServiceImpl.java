package com.sample.cache;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheServiceImpl<T> implements RedisCacheService<T> {

	@Resource(name = "redisTemplate")
	private ValueOperations<String, T> valueOps;

	/**
	 * cache the data using value operations
	 */
	@Override
	public void cacheStudent(String key, T value) {
		Duration timeOut = Duration.of(30, ChronoUnit.SECONDS);
		valueOps.set(key, value, timeOut);
	}

	/**
	 * gets the cache using value operations
	 */
	@Override
	public T getCachedStudent(String key) {
		return valueOps.get(key);
	}

	/**
	 * checks the availability of cache using value operations
	 */
	@Override
	public boolean isStudentCached(String key) {
		return valueOps.get(key) != null;
	}
}
