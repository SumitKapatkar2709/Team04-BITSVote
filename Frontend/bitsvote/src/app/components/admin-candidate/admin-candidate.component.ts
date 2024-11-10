import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CandidateService } from '../../services/candidate.service';
import { Candidate } from '../../models/candidate.model';
import { VotingService } from '../../services/voting.service';

@Component({
  selector: 'app-admin-candidate',
  templateUrl: './admin-candidate.component.html',
  styleUrls: ['./admin-candidate.component.scss']
})
export class AdminCandidateComponent implements OnInit {

  electionId: number | null = null;
  electionName: string = '';
  candidates: Candidate[] = [];
  isAddingCandidate: boolean = false;
  newCandidate: Candidate = {
    candidateId: 0,
    candidateName: '',
    votes: 0,  // votes are set to 0 by default
    electionId: 0
  };

  constructor(
    private route: ActivatedRoute,
    private candidateService: CandidateService,
    private votingService: VotingService
  ) {}

  ngOnInit(): void {
    this.electionId = Number(this.route.snapshot.paramMap.get('electionId'));
    if (this.electionId) {
      this.getElectionName();
      this.getCandidates();
    }
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

  getCandidates(): void {
    if (this.electionId) {
      this.candidateService.getCandidatesByElectionId(this.electionId).subscribe({
        next: (candidates) => {
          this.candidates = candidates;
        },
        error: (error) => {
          console.error('Error fetching candidates:', error);
        }
      });
    }
  }

  toggleAddCandidateForm(): void {
    this.isAddingCandidate = !this.isAddingCandidate;
  }

  addCandidate(): void {
    if (this.newCandidate.candidateName && this.electionId) {
      this.newCandidate.electionId = this.electionId;  // Set election ID for new candidate
      this.candidateService.addCandidate(this.newCandidate).subscribe({
        next: (candidate) => {
          this.candidates.push(candidate);
          this.resetNewCandidateForm();
          this.toggleAddCandidateForm();
        },
        error: (error) => {
          console.error('Error adding candidate:', error);
        }
      });
    }
  }

  deleteCandidate(candidateId: number): void {
    this.candidateService.deleteCandidate(candidateId).subscribe({
      next: () => {
        this.candidates = this.candidates.filter(c => c.candidateId !== candidateId);
      },
      error: (error) => {
        console.error('Error deleting candidate:', error);
      }
    });
  }

  resetNewCandidateForm(): void {
    this.newCandidate = {
      candidateId: 0,
      candidateName: '',
      votes: 0,  // Reset votes to 0
      electionId: 0
    };
  }
}
