import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Candidate } from '../models/candidate.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CandidateService {

  private apiUrl = environment.apiBaseUrl + '/candidate';

  constructor(private http: HttpClient) { }

  getCandidatesByElectionId(electionId: number): Observable<Candidate[]> {
    return this.http.get<Candidate[]>(`${this.apiUrl}/${electionId}`);
  }

  vote(candidateId: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/vote/${candidateId}`, {});
  }

  recordStudentVote(studentId: number, electionId: number): Observable<any> {
    const url = `${environment.apiBaseUrl}/elections/record?studentId=${studentId}&electionId=${electionId}`;
    return this.http.post(url, null);
  }

  getAdminCandidatesByElectionId(electionId: number): Observable<Candidate[]> {
    return this.http.get<Candidate[]>(`${this.apiUrl}/elections/${electionId}/candidates`);
  }

  addCandidate(candidate: Candidate): Observable<Candidate> {
    return this.http.post<Candidate>(`${this.apiUrl}/add`, candidate);
  }

  deleteCandidate(candidateId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${candidateId}`);
  }

  getWinnerByElectionId(electionId: number): Observable<Candidate> {
    return this.http.get<Candidate>(`${this.apiUrl}/winner/${electionId}`);
  }

  

  
  
}
