package com.bits.bitsvote.service;

import com.bits.bitsvote.entity.Elections;

import java.util.List;

public interface ElectionService {

    // Method to fetch all elections
    List<Elections> getAllElections();

    // Other service methods can be added here, for example, to fetch elections by ID or status
    Elections getElectionById(Long electionId);

	Elections updateElection(Long id, Elections updatedElection);

	List<Elections> getElectionsByStatus(String string);

	
	

    // Add any other necessary methods based on requirements
}
