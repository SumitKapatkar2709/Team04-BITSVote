import { Component, OnInit } from '@angular/core';
import { VotingService } from '../../services/voting.service';
import { CandidateService } from '../../services/candidate.service';
import { Election } from '../../services/voting.service';
import { Candidate } from '../../models/candidate.model';

@Component({
  selector: 'app-election-result',
  templateUrl: './election-result.component.html',
  styleUrls: ['./election-result.component.scss']
})
export class ElectionResultComponent implements OnInit {
  completedElections: Election[] = [];
  isPopupVisible: boolean = false;
  winner: Candidate | null = null;

  constructor(
    private electionService: VotingService,
    private candidateService: CandidateService
  ) { }

  ngOnInit(): void {
    this.getCompletedElections();
  }

  getCompletedElections(): void {
    this.electionService.getElectionsByStatus('completed').subscribe(
      (elections: Election[]) => {
        this.completedElections = elections;
      },
      (error) => {
        console.error('Error fetching completed elections:', error);
      }
    );
  }

  viewResults(electionId: number): void {
    this.candidateService.getWinnerByElectionId(electionId).subscribe(
      (winner: Candidate) => {
        this.winner = winner ? winner : null;
        this.isPopupVisible = true;
      },
      (error) => {
        console.error('Error fetching election results:', error);
      }
    );
  }

  closePopup(): void {
    this.isPopupVisible = false;
  }
}
