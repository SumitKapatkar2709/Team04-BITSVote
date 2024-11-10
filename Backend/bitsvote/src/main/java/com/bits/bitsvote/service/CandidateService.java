package com.bits.bitsvote.service;

import java.util.List;

import com.bits.bitsvote.DTO.Request.CandidateRequestDTO;
import com.bits.bitsvote.entity.Candidate;

public interface CandidateService {

	List<Candidate> getCandidatesByElectionId(Long electionId);

	void incrementVote(Long candidateId);

	String addCandidate(CandidateRequestDTO candidate);

	void deleteCandidate(Long candidateId);

	Candidate findWinnerByElectionId(Long electionId);
}
