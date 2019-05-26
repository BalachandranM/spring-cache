package com.sample.cache;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheServiceImpl<T> implements RedisCacheService<T> {

	@Resource(name = "redisTemplate")
	private ListOperations<String, T> messageList;
	
	@Resource(name = "redisTemplate")
	private HashOperations<String, String, T> hashOps;

	@Resource(name = "redisTemplate")
	private RedisOperations<String, T> latestMessageExpiration;

	@Override
	public void addMessage(String key, T value) {
		messageList.leftPush(key, value);
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		Date date = Date.from(zonedDateTime.plus(1, ChronoUnit.MINUTES).toInstant());
		latestMessageExpiration.expireAt(key, date);
	}

	@Override
	public List<T> listMessages(String key) {
		return messageList.range(key, 0, -1);
	}

	@Override
	public void cacheStudent(String groupName, String key, T value) {
		hashOps.put(groupName, key, value);
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		Date date = Date.from(zonedDateTime.plus(30, ChronoUnit.SECONDS).toInstant());
		latestMessageExpiration.expireAt(key,date);
	}

	@Override
	public boolean isStudentCached(String groupName, String key) {
		return hashOps.hasKey(groupName, key);
	}

	@Override
	public T getCachedStudent(String groupName, String key) {
		return hashOps.get(groupName, key);
	}
}
