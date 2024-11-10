package com.bits.bitsvote.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidates")
public class Candidate {

    
    @Column(name = "candidate_id")
    @Id
	@SequenceGenerator(name = "candidate_seq", sequenceName = "candidate_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidate_seq")
    private Long candidateId;

    @Column(name = "candidate_name", nullable = false, length = 100)
    private String candidateName;

    @Column(name = "votes", nullable = false)
    private Long votes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "election_id", nullable = false)  // FK to elections table
    private Elections election;

    // Constructors
    public Candidate() {}

    public Candidate(String candidateName, Long votes) {
        this.candidateName = candidateName;
        this.votes = votes;
    }

    // Getters and Setters
    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Elections getElection() {
		return election;
	}

	public void setElection(Elections election) {
		this.election = election;
	}

	public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }
}

