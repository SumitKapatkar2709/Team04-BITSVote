package com.bits.bitsvote.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_name", nullable = false, length = 100)
    private String studentName;
    
    @Column(name = "student_hostel", length = 100)
    private String studentHostel;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_election", // Name of the join table
        joinColumns = @JoinColumn(name = "student_id"), // Foreign key to Student entity
        inverseJoinColumns = @JoinColumn(name = "election_id") // Foreign key to Elections entity
    )
    private Set<Elections> elections;

    public Student() {
        // Default constructor
    }

    public Student(Long studentId, String studentName, String studentHostel, String email, Set<Elections> elections) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentHostel = studentHostel;
        this.email = email;
        this.elections = elections;
    }

    // Getters and Setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentHostel() {
        return studentHostel;
    }

    public void setStudentHostel(String studentHostel) {
        this.studentHostel = studentHostel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Elections> getElections() {
        return elections;
    }

    public void setElections(Set<Elections> elections) {
        this.elections = elections;
    }
}
