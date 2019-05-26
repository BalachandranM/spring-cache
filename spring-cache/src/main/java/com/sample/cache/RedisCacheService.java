package com.sample.cache;

public interface RedisCacheService<T> {

	public void cacheStudent(String key, T value);

	public T getCachedStudent(String key);

	public boolean isStudentCached(String key);
}
