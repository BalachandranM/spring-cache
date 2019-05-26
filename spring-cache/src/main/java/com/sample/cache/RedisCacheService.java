package com.sample.cache;

import java.util.List;

public interface RedisCacheService<T> {

	public void addMessage(String key, T value);

	public List<T> listMessages(String user);
	
	public void cacheStudent(String groupName,String key,T value);
	
	public boolean isStudentCached(String groupName,String key);
	
	public T getCachedStudent(String groupName,String key);

}
