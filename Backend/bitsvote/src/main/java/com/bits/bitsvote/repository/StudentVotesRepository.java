package com.bits.bitsvote.repository;

import com.bits.bitsvote.entity.StudentVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentVotesRepository extends JpaRepository<StudentVotes, Long> {
    List<StudentVotes> findByStudentStudentId(Long studentId);
    List<StudentVotes> findByElectionElectionId(Long electionId);
    boolean existsByStudentStudentIdAndElectionElectionId(Long studentId, Long electionId);
    
    boolean existsByStudent_StudentIdAndElection_ElectionIdAndHasVotedTrue(Long studentId, Long electionId);}
