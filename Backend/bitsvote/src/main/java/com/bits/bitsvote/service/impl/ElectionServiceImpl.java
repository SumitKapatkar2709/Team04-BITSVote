package com.bits.bitsvote.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.bitsvote.entity.Elections;
import com.bits.bitsvote.entity.Student;
import com.bits.bitsvote.entity.StudentVotes;
import com.bits.bitsvote.repository.ElectionRepository;
import com.bits.bitsvote.repository.StudentVotesRepository;
import com.bits.bitsvote.service.ElectionService;
import com.bits.bitsvote.service.StudentService;

@Service
public class ElectionServiceImpl implements ElectionService {

	
	@Autowired
    private StudentVotesRepository studentVotesRepository;
	
	@Autowired
    private StudentService studentService;
	
	
	
    private final ElectionRepository electionRepository;

    @Autowired
    public ElectionServiceImpl(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    @Override
    public List<Elections> getAllElections() {
        return electionRepository.findAll();  // Fetch all elections from the repository
    }

    @Override
    public Elections getElectionById(Long electionId) {
        return electionRepository.findById(electionId).orElse(null);  // Find election by ID
    }
    
    public List<Elections> getElectionsByStatus(String status) {
        return electionRepository.findByStatus(status);
    }
    
    @Override
    public Elections updateElection(Long id, Elections updatedElection) {
        return electionRepository.findById(id).map(election -> {
            election.setElectionName(updatedElection.getElectionName());
            election.setStatus(updatedElection.getStatus());
            election.setStartTime(updatedElection.getStartTime());
            election.setEndTime(updatedElection.getEndTime());
            election.setEventDetails(updatedElection.getEventDetails());
            return electionRepository.save(election);
        }).orElseThrow(() -> new RuntimeException("Election not found with id " + id));
    }
    
}
