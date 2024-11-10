package com.bits.bitsvote.service.impl;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.bitsvote.DTO.Request.CandidateRequestDTO;
import com.bits.bitsvote.entity.Candidate;
import com.bits.bitsvote.entity.Elections;
import com.bits.bitsvote.repository.CandidateRepository;
import com.bits.bitsvote.repository.ElectionRepository;
import com.bits.bitsvote.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
    CandidateRepository candidateRepository;
	
	@Autowired
    ElectionRepository electionRepository;


    @Override
    public List<Candidate> getCandidatesByElectionId(Long electionId) {
        return candidateRepository.findByElectionId(electionId);
    }
    
    @Override
    public void incrementVote(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow();
        candidate.setVotes(candidate.getVotes() + 1);
        candidateRepository.save(candidate);
    }
    
    @Override
    public String addCandidate(CandidateRequestDTO candidateRequestDTO) {
        // Fetch the Election entity by electionId
        Elections election = electionRepository.findById(candidateRequestDTO.getElectionId())
                .orElseThrow(() -> new RuntimeException("Election not found"));

        // Create the new candidate entity
        Candidate candidate = new Candidate();
        candidate.setCandidateName(candidateRequestDTO.getCandidateName()); // Set candidate's name
        candidate.setVotes(0L);  // Set the initial votes to 0
        candidate.setElection(election);  // Set the associated election

        // Save the candidate in the repository
        
        candidateRepository.save(candidate);
        return "Candidate Added Successfully!!!";
    }

    // Method to delete a candidate
    @Override
    public void deleteCandidate(Long candidateId) {
        candidateRepository.deleteById(candidateId);
    }
    
    public Candidate findWinnerByElectionId(Long electionId) {
        List<Candidate> candidates = candidateRepository.findByElectionId(electionId);

        // If no candidates are found, return null
        if (candidates.isEmpty()) {
            return null;
        }

        // Find the candidate with the maximum votes
        return candidates.stream()
                         .max(Comparator.comparingLong(Candidate::getVotes))
                         .orElse(null);
    }

}
