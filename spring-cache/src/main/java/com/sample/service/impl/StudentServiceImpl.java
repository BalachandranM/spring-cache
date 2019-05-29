package com.sample.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sample.bean.Student;
import com.sample.cache.RedisCacheService;
import com.sample.repo.StudentRepo;
import com.sample.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	Logger logger = LogManager.getLogger(StudentServiceImpl.class);

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	RedisCacheService<Student> redisCache;

	@Override
	public List<Student> getAllStudents() {
		logger.info("Getting all students from Repo");
		return studentRepo.findAll();
	}

	@Override
	public Student getStudent(Long id) {
		Student s = null;
		if (!redisCache.isStudentCached(Long.toString(id))) {
			logger.info("Getting student data from repo");
			s = studentRepo.findById(id).orElse(null);
			if (s != null)
				redisCache.cacheStudent(Long.toString(s.getId()), s);
		} else {
			logger.info("Getting student data from CACHE");
			s = redisCache.getCachedStudent(Long.toString(id));
		}
		return s;
	}

	@Override
	public Student saveStudent(Student newStudent) {
		logger.info("Saving student data to REPO");
		Student s = studentRepo.save(newStudent);
		return s;
	}

	@Override
	@Cacheable(value = "STUDENT", key = "#id.toString()", cacheManager = "cacheManager")
	public Student getStudentWithAnnotation(Long id) {
		logger.info("Getting student data from repo");
		Student s = studentRepo.findById(id).orElse(null);
		return s;
	}
}
