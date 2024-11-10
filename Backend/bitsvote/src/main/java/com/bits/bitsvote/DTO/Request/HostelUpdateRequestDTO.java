package com.bits.bitsvote.DTO.Request;

public class HostelUpdateRequestDTO {

    private Long studentId;
    private String hostel;

    // Getters and setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }
}

