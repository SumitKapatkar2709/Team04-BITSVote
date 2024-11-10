import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Elections } from '../models/election.model';
import { Candidate } from '../models/candidate.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private apiUrl = '/api/admin';

  constructor(private http: HttpClient) {}

  // Get ongoing elections
  getOngoingElections(): Observable<Elections[]> {
    return this.http.get<Elections[]>(`${this.apiUrl}/ongoing-elections`);
  }

  // Add a new election
  addElection(election: Elections): Observable<Elections> {
    return this.http.post<Elections>(`${this.apiUrl}/elections`, election);
  }

  // Get candidates by election ID
  getCandidatesByElectionId(electionId: number): Observable<Candidate[]> {
    return this.http.get<Candidate[]>(`${this.apiUrl}/candidates/${electionId}`);
  }

  // Add a new candidate
  addCandidate(candidate: Candidate): Observable<Candidate> {
    return this.http.post<Candidate>(`${this.apiUrl}/add-candidate`, candidate);
  }

  // Delete candidate by ID
  deleteCandidate(candidateId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete-candidate/${candidateId}`);
  }
}
