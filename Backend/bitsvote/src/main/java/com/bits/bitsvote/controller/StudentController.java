package com.bits.bitsvote.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bits.bitsvote.DTO.Request.HostelUpdateRequestDTO;
import com.bits.bitsvote.entity.Student;
import com.bits.bitsvote.service.StudentService;

@RestController
//@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Endpoint to fetch authenticated student details
    @GetMapping("/api/students/me")
    public ResponseEntity<Student> getAuthenticatedStudent(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        // Fetch student by email
        Student student = studentService.findByEmail(email);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint to update the student's hostel
    @PostMapping("elections/update-hostel")
    public ResponseEntity<Student> updateHostel(@RequestBody HostelUpdateRequestDTO request) {
        // Assuming the HostelUpdateRequest class has studentId and hostel fields
        Student student = studentService.updateHostel(request.getStudentId(), request.getHostel());
        
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

