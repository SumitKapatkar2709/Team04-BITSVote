import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CandidateService } from '../../services/candidate.service';
import { Candidate } from '../../models/candidate.model';
import { VotingService } from '../../services/voting.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-candidate-dashboard',
  templateUrl: './candidate-dashboard.component.html',
  styleUrls: ['./candidate-dashboard.component.scss']
})
export class CandidateDashboardComponent implements OnInit {
  electionId: number;
  electionName: string = '';
  candidates: Candidate[] = [];
  hasVoted = false;
  showPopup = false;
  studentId: number | null = null;
  hostelStatus = '';
  hostel = '';


  constructor(
    private route: ActivatedRoute,
    private candidateService: CandidateService,
    private votingService: VotingService,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.electionId = +this.route.snapshot.paramMap.get('electionId')!;
    this.getCandidates();
    this.getElectionName();

    this.http.get<{ studentId: number, studentHostel: string }>(`/api/students/me`).subscribe(
      (student) => {
        this.studentId = student.studentId;
        
        // If student already has a hostel assigned, redirect to election dashboard
        if (student.studentHostel) {
          this.hostel = student.studentHostel;
          // this.router.navigate(['/voting-dashboard']);
        }
      },
      (error) => {
        console.error('Failed to fetch student information', error);
      }
    );
  }

  getCandidates(): void {
    this.candidateService.getCandidatesByElectionId(this.electionId)
      .subscribe(candidates => {
        this.candidates = candidates;
      });
  }

  voteForCandidate(candidateId: number): void {
    if (this.hasVoted) return;
  
    // const studentId = 7 // Assume studentId is stored in localStorage
  
    console.log('Voting for candidate with ID:', candidateId);
  
    // Step 1: Call the existing candidate voting method
    this.candidateService.vote(candidateId).subscribe(
      (response) => {
        console.log(`Voted for candidate with ID ${candidateId}`);
        this.hasVoted = true;
            this.showPopup = true; 
  
        // Step 2: Record the vote with respect to student and election ID
        this.candidateService.recordStudentVote(this.studentId!, this.electionId)
          .subscribe(() => {
            // Show success popup
          }, error => {
            console.error('Error recording student vote:', error);
          });
      },
      (error) => {
        console.error('Error voting for candidate:', error);
      }
    );
  }
  


  getElectionName(): void {
    this.votingService.getElectionById(this.electionId!).subscribe({
      next: (election) => {
        this.electionName = election.electionName;
      },
      error: (error) => {
        console.error('Error fetching election name:', error);
      }
    });
  }

  closePopup() {
    this.showPopup = false;
  }
}
