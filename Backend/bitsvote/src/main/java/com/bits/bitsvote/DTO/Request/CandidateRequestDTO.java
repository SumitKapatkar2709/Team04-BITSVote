package com.bits.bitsvote.DTO.Request;

public class CandidateRequestDTO {

	private Long electionId;
	private String candidateName;
	
	
	
	public CandidateRequestDTO(Long electionId, String candidateName) {
		super();
		this.electionId = electionId;
		this.candidateName = candidateName;
	}
	
	
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	
}
