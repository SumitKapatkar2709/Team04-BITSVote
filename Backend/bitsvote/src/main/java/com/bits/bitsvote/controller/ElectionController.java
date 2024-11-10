package com.bits.bitsvote.controller;

import java.time.LocalDateTime;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bits.bitsvote.entity.Elections;
import com.bits.bitsvote.entity.Student;
import com.bits.bitsvote.entity.StudentVotes;
import com.bits.bitsvote.repository.ElectionRepository;
import com.bits.bitsvote.repository.StudentRepository;
import com.bits.bitsvote.repository.StudentVotesRepository;
import com.bits.bitsvote.service.ElectionService;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@RestController
//@RequestMapping("/elections")
public class ElectionController {

	@Autowired
	ElectionService electionService;
	
	@Autowired
    private ElectionRepository electionRepository;
	
	@Autowired
    private StudentVotesRepository studentVotesRepository;
	
	 

	    @Autowired
	    private StudentRepository studentRepository;

	    @Autowired
	    private ElectionRepository electionsRepository;


    

    // GET request to fetch all elections
    @GetMapping("/elections")
    public List<Elections> getAllElections() {
        return electionService.getAllElections();
    }
    
    @GetMapping("/elections/{id}")
    public Elections getElectionById(@PathVariable Long id) {
        return electionService.getElectionById(id);
    }
    
    @GetMapping("/elections/completed")
    public List<Elections> getCompletedElections() {
        return electionService.getElectionsByStatus("completed");
    }
    
    @PostMapping("/elections/add")
    public ResponseEntity<Elections> addElection(@RequestBody Elections election) {
        Elections savedElection = electionRepository.save(election);
        return ResponseEntity.ok(savedElection);
    }
    
    @PutMapping("/elections/{id}")
    public ResponseEntity<Elections> updateElection(
            @PathVariable Long id,
            @RequestBody Elections updatedElection) {
        Elections updated = electionService.updateElection(id, updatedElection);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/elections/has-voted")
    public boolean hasStudentVoted(
            @RequestParam Long studentId, 
            @RequestParam Long electionId) {
        
        return studentVotesRepository.existsByStudent_StudentIdAndElection_ElectionIdAndHasVotedTrue(studentId, electionId);
    }
    
    @PostMapping("/elections/record")
    public ResponseEntity<String> recordVote(
            @RequestParam Long studentId,
            @RequestParam Long electionId) {
        
        // Check if the student has already voted in this election
        boolean hasAlreadyVoted = studentVotesRepository.existsByStudentStudentIdAndElectionElectionId(studentId, electionId);
        if (hasAlreadyVoted) {
            return ResponseEntity.badRequest().body("Student has already voted in this election.");
        }

        // Fetch Student and Election entities
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Elections election = electionsRepository.findById(electionId)
                .orElseThrow(() -> new RuntimeException("Election not found"));

        // Record the vote
        StudentVotes vote = new StudentVotes(student, election, true, LocalDateTime.now());
        studentVotesRepository.save(vote);

        return ResponseEntity.ok("Vote recorded successfully");
    }
    
    @DeleteMapping("/elections/delete/{id}")
    public ResponseEntity<Void> deleteElection(@PathVariable Long id) {
        if (!electionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        electionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
