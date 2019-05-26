package com.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sample.bean.Student;
import com.sample.cache.RedisCacheService;
import com.sample.repo.StudentRepo;
import com.sample.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	private static final String groupName = "STUDENT";

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	RedisCacheService<Student> redisCache; 

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	@Cacheable(value = "STUDENT", key = "#id.toString()", cacheManager ="pickleCacheManager")
	public Student getStudent(Long id) {
		Student s = null;
		if (!redisCache.isStudentCached(groupName, Long.toString(id))) {
			s = studentRepo.findById(id).orElse(null);
			redisCache.cacheStudent(groupName, Long.toString(s.getId()), s);
		} else {
			s = redisCache.getCachedStudent(groupName, Long.toString(id));
		}
		return s;
	}

	@Override
	public Student saveStudent(Student newStudent) {
		Student s = studentRepo.save(newStudent);
		redisCache.cacheStudent(groupName, Long.toString(s.getId()), s);
		return s;
	}

}
