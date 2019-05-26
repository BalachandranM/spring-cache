package com.sample.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sample.bean.Student;
import com.sample.service.StudentService;

@RestController
public class CacheController implements CacheApi {

	@Autowired
	StudentService studentService;

	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> studentList = studentService.getAllStudents();
		return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Student> getStudent(Long id) {
		Student student = studentService.getStudent(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Student> saveStudent(Student student) {
		Student savedStudent = studentService.saveStudent(student);
		return new ResponseEntity<Student>(savedStudent, HttpStatus.OK);
	}

}
