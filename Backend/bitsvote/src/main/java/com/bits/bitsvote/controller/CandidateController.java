package com.bits.bitsvote.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bits.bitsvote.DTO.Request.CandidateRequestDTO;
import com.bits.bitsvote.entity.Candidate;
import com.bits.bitsvote.service.CandidateService;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@RestController
public class CandidateController {

	@Autowired
	CandidateService candidateService;
	
	 @GetMapping("/candidate/{electionId}")
	    public List<Candidate> getCandidatesByElectionId(@PathVariable Long electionId) {
	        return candidateService.getCandidatesByElectionId(electionId);
	    }
	 
	 @ResponseBody
	 @PutMapping("candidate/vote/{candidateId}")
	 public ResponseEntity<Map<String, String>> voteForCandidate(@PathVariable Long candidateId) {
		    Map<String, String> response = new HashMap<>();
		    response.put("message", "Vote cast successfully");
		    return ResponseEntity.ok(response);
		}
	 
	 @PostMapping("/candidate/add")
	    public ResponseEntity<String> addCandidate(@RequestBody CandidateRequestDTO candidate) {
	        String createdCandidate = candidateService.addCandidate(candidate);
	        return ResponseEntity.ok(createdCandidate);
	    }

	    // Endpoint to delete a candidate
	    @DeleteMapping("/candidate/{candidateId}")
	    public ResponseEntity<Void> deleteCandidate(@PathVariable Long candidateId) {
	        candidateService.deleteCandidate(candidateId);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @GetMapping("/candidate/winner/{electionId}")
	    public ResponseEntity<Candidate> getWinnerByElectionId(@PathVariable Long electionId) {
	        Candidate winner = candidateService.findWinnerByElectionId(electionId);
	        if (winner != null) {
	            return ResponseEntity.ok(winner);  // Return winner details if found
	        } else {
	            return ResponseEntity.notFound().build();  // Return 404 if no winner found
	        }
	    }
}
