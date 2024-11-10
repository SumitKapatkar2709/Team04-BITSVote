package com.bits.bitsvote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bits.bitsvote.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

//	List<Candidate> findByElectionId(Long electionId);

	List<Candidate> findByElection_ElectionId(Long electionId);
	
	@Query("SELECT c FROM Candidate c JOIN FETCH c.election WHERE c.election.id = :electionId")
	List<Candidate> findByElectionId(@Param("electionId") Long electionId);
}
