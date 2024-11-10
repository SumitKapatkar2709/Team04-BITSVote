import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { VotingService, Election } from '../../services/voting.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-voting-dashboard',
  templateUrl: './voting-dashboard.component.html',
  styleUrls: ['./voting-dashboard.component.scss']
})
export class VotingDashboardComponent implements OnInit {
  elections: Election[] = [];
  filteredElections: Election[] = [];
  selectedElectionType: string = ''; // Default is "All"
  hostel = '';
  studentId: number | null = null;
  hostelStatus = '';
  votedElections: Set<number> = new Set();

  isCollapsed = true;
  dropdownOpen = false;

  // Toggle collapse on mouse hover
  hoverSidebar(isHovering: boolean): void {
    this.isCollapsed = !isHovering;
  }

  // Toggle the dropdown for Doctor Management
  toggleDropdown(): void {
    this.dropdownOpen = !this.dropdownOpen;
  }


  constructor(
    private votingService: VotingService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.fetchElections();

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

    // this.hostel = 'Hostel General Election';
    // this.studentId = 7;
    
  }

  canVote(election: Election): boolean {
    if (this.votedElections.has(election.electionId)) {
      return false; // Disable if already voted
    }
    if (election.electionType === 'Hostel' || election.electionType === 'Mess') {
      return election.electionName === this.hostel;
    }
    return true;
  }

  fetchElections(): void {
    this.votingService.getAllElections().subscribe(
      (data) => {
        this.elections = data;
        this.filteredElections = data;
        this.fetchVotingStatus(); // Show all elections by default
      },
      (error) => {
        console.error('Error fetching elections:', error);
      }
    );
  }

  fetchVotingStatus(): void {
    if (this.studentId !== null) {
      this.elections.forEach((election) => {

        console.log("stude",this.studentId);
        console.log("elec",election.electionId);
        this.checkIfStudentVoted(this.studentId!, election.electionId);
      });
    }
  }

  checkIfStudentVoted(studentId: number, electionId: number): void {
    this.http
      .get<boolean>(`${environment.apiBaseUrl}/elections/has-voted?studentId=${studentId}&electionId=${electionId}`)
      .subscribe(
        (hasVoted) => {
          if (hasVoted) {
            this.votedElections.add(electionId);
          }
        },
        (error) => {
          console.error(`Error checking vote status for election ${electionId}`, error);
        }
      );
    }

  

  filterElections(): void {
    if (this.selectedElectionType) {
      this.filteredElections = this.elections.filter(
        election => election.electionType === this.selectedElectionType
      );
    } else {
      this.filteredElections = this.elections; // Show all elections if no filter is selected
    }
  }

  viewCandidates(electionId: number): void {
    this.router.navigate([`/elections/${electionId}/candidates`]);
  }

  navigateTo(page: string): void {
    if (page === 'elections') {
      this.router.navigate(['/voting-dashboard']); // Redirect to current page (Voting Dashboard)
    } else if (page === 'results') {
      this.router.navigate(['/results']); // Implement your results page routing here
    }
  }
}
