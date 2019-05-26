package com.sample.service;

import java.util.List;

import com.sample.bean.Student;

public interface StudentService {

	public List<Student> getAllStudents();

	public Student getStudent(Long id);

	public Student saveStudent(Student newStudent);
}
