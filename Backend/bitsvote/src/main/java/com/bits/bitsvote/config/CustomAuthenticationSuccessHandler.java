package com.bits.bitsvote.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bits.bitsvote.entity.Student;
import com.bits.bitsvote.repository.StudentRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	

    @Autowired
    private StudentRepository studentRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
	    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
	    String email = oAuth2User.getAttribute("email"); // Ensure this attribute is present
	    
	    String studentName = oAuth2User.getAttribute("name");
	    String studenEmail = oAuth2User.getAttribute("email");

	    // Log the email to ensure it's being fetched correctly
	    System.out.println("Authenticated Email: " + email);

	    // Check if the email domain matches
	    if (email != null && email.endsWith("@pilani.bits-pilani.ac.in")) {
	    	
	    	if (studentRepository.findByStudentName(studentName) == null) {
	            Student student = new Student();
	            student.setStudentName(studentName);
	            student.setEmail(studenEmail);
	            studentRepository.save(student);
	        }

	        // Redirect to the student login page
	        response.sendRedirect("/");
	    

	    
//	        response.sendRedirect("/"); // Redirect to home if email domain is valid
	    } else {
	    	request.getSession().invalidate();
	        response.sendRedirect("/access-denied"); // Redirect to access-denied page if domain is invalid
	    }
	}
}

