package com.sample.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.bean.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

}
