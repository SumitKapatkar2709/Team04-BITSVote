package com.bits.bitsvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.bitsvote.entity.Student;
import com.bits.bitsvote.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student updateHostel(Long studentId, String hostel) {
        // Retrieve student by studentId
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
            student.setStudentHostel(hostel);
            studentRepository.save(student);
        }

        return student;
    }

	public Student getStudentById(Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}
}

