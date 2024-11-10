package com.bits.bitsvote.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "elections")
public class Elections {

    @Id
    @SequenceGenerator(name = "elections_seq", sequenceName = "elections_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elections_seq")
    @Column(name = "election_id")
    private Long electionId;

    @Column(name = "election_name", nullable = false, length = 100)
    private String electionName;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "event_details", length = 500)
    private String eventDetails;
    
    @Column(name = "election_type", length = 500)
    private String electionType;

    // Constructors
    public Elections() {}

    

    public String getElectionType() {
		return electionType;
	}



	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}



	public Elections(Long electionId, String electionName, String status, LocalDateTime startTime,
			LocalDateTime endTime, String eventDetails, String electionType) {
		super();
		this.electionId = electionId;
		this.electionName = electionName;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.eventDetails = eventDetails;
		this.electionType = electionType;
	}



	// Getters and Setters
    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getStatus() {
        updateStatus();  // Ensure status is updated based on current time
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        updateStatus();  // Update status whenever start or end times change
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        updateStatus();  // Update status whenever start or end times change
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    // Method to automatically update the status based on current time
    @PrePersist
    @PreUpdate
    private void updateStatus() {
        LocalDateTime now = LocalDateTime.now();

        if (this.startTime == null || this.endTime == null) {
            this.status = "unknown";  // or any default status
        } else if (now.isBefore(this.startTime)) {
            this.status = "upcoming";
        } else if (now.isAfter(this.startTime) && now.isBefore(this.endTime)) {
            this.status = "ongoing";
        } else if (now.isAfter(this.endTime)) {
            this.status = "completed";
        }
    }
}
