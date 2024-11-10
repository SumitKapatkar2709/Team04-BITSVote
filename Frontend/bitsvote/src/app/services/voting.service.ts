import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Election {
  electionId: number;
  electionName: string;
  status: string;
  startTime: string;
  endTime: string;
  eventDetails: string;
  electionType: string;
}

@Injectable({
  providedIn: 'root'
})
export class VotingService {
  private apiUrl = `${environment.apiBaseUrl}/elections`;

  constructor(private http: HttpClient) {}

  getAllElections(): Observable<Election[]> {
    return this.http.get<Election[]>(this.apiUrl);
  }

  addElection(election: Election): Observable<Election> {
    return this.http.post<Election>(`${this.apiUrl}/add`, election);
  }

  updateElection(id: number, election: Election): Observable<Election> {
    return this.http.put<Election>(`${this.apiUrl}/${id}`, election);
  }

  getElectionById(id: number): Observable<Election> {
    return this.http.get<Election>(`${this.apiUrl}/${id}`);
  }

  getElectionsByStatus(status: string): Observable<Election[]> {
    return this.http.get<Election[]>(`${this.apiUrl}/completed`);
  }

  deleteElection(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  

  
}
