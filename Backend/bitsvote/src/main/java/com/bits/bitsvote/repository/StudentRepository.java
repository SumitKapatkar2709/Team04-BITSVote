package com.bits.bitsvote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bits.bitsvote.entity.Student;

@Repository
public interface  StudentRepository extends JpaRepository<Student, Long>{
	
	Student findByStudentName(String name);

	Student findByEmail(String email);

}
