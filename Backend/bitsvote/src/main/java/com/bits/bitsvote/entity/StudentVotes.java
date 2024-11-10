package com.bits.bitsvote.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_votes")
public class StudentVotes {

    @Id
    @SequenceGenerator(name = "student_votes_seq", sequenceName = "student_votes_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_votes_seq")
    @Column(name = "vote_id")
    private Long voteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "election_id", nullable = false)
    private Elections election;

    @Column(name = "has_voted", nullable = false)
    private boolean hasVoted;

    @Column(name = "vote_time")
    private LocalDateTime voteTime;

 

    // Constructors
    public StudentVotes() {}

    public StudentVotes(Student student, Elections election, boolean hasVoted, LocalDateTime voteTime) {
        this.student = student;
        this.election = election;
        this.hasVoted = hasVoted;
        this.voteTime = voteTime;
    }

    // Getters and Setters
    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Elections getElection() {
        return election;
    }

    public void setElection(Elections election) {
        this.election = election;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public LocalDateTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(LocalDateTime voteTime) {
        this.voteTime = voteTime;
    }
}

