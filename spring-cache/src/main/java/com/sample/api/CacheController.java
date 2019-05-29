package com.sample.api;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.sample.bean.Student;
import com.sample.service.StudentService;

@RestController
public class CacheController implements CacheApi {

	Logger logger = LogManager.getLogger(CacheController.class);

	@Autowired
	StudentService studentService;

	public ResponseEntity<List<Student>> getAllStudents() {
		logger.info("Invoked getAllStudents service");
		List<Student> studentList = studentService.getAllStudents();
		logger.info("Response Time: " + new Date());
		return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Student> getStudent(Long id) {
		logger.info("Invoked getStudent service");
		Student student = studentService.getStudent(id);
		logger.info("Response Time: " + new Date());
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Student> getStudentUsingCacheManager(Long id) {
		logger.info("Invoked getStudent service");
		Student student = studentService.getStudentWithAnnotation(id);
		logger.info("Response Time: " + new Date());
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Student> saveStudent(Student student) {
		logger.info("Invoked saveStudent service");
		Student savedStudent = studentService.saveStudent(student);
		logger.info("Response Time: " + new Date());
		return new ResponseEntity<Student>(savedStudent, HttpStatus.OK);
	}

}
