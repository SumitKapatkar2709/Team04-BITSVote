import { Component, OnInit } from '@angular/core';
import { VotingService, Election } from '../../services/voting.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {

  elections: Election[] = [];
  newElection: Election = {
    electionId: 0,
    electionName: '',
    status: 'upcoming',
    startTime: '',
    endTime: '',
    eventDetails: '',
    electionType: ''
  };
  selectedElection: Election | null = null;  // Holds the election being edited
  isViewingAllElections: boolean = true;

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


  constructor(private votingService: VotingService, private router: Router) {}

  ngOnInit(): void {
    this.getElections();
  }

  getElections(): void {
    this.votingService.getAllElections().subscribe({
      next: (elections) => {
        this.elections = elections;
      },
      error: (error) => {
        console.error('Error fetching elections:', error);
      }
    });
  }

  showAllElections(): void {
    this.isViewingAllElections = true;
    this.selectedElection = null;  // Hide edit form
  }

  showAddElection(): void {
    this.isViewingAllElections = false;
  }

  addElection(): void {
    this.votingService.addElection(this.newElection).subscribe({
      next: (election) => {
        this.elections.push(election);
        this.resetForm();
        this.showAllElections();
      },
      error: (error) => {
        console.error('Error adding election:', error);
      }
    });
  }

  resetForm(): void {
    this.newElection = {
      electionId: 0,
      electionName: '',
      status: 'upcoming',
      startTime: '',
      endTime: '',
      eventDetails: '',
      electionType: ''
    };
  }

  // Method to set the selected election for editing
  editElection(election: Election): void {
    this.selectedElection = { ...election };  // Create a copy to avoid immediate changes
  }

  // Method to save the edited election to the backend
  updateElection(): void {
    if (this.selectedElection) {
      this.votingService.updateElection(this.selectedElection.electionId, this.selectedElection).subscribe({
        next: (updatedElection) => {
          // Replace the updated election in the list
          const index = this.elections.findIndex(e => e.electionId === updatedElection.electionId);
          if (index !== -1) {
            this.elections[index] = updatedElection;
          }
          this.selectedElection = null;  // Hide the edit form
        },
        error: (error) => {
          console.error('Error updating election:', error);
        }
      });
    }
  }

  // Method to cancel the edit operation
  cancelEdit(): void {
    this.selectedElection = null;
  }

  viewCandidates(electionId: number): void {
    this.router.navigate(['/admin-candidate', electionId]);
  }

  deleteElection(electionId: number): void {
    if (confirm('Are you sure you want to delete this election?')) {
      this.votingService.deleteElection(electionId).subscribe({
        next: () => {
          // Remove the deleted election from the list
          this.elections = this.elections.filter(election => election.electionId !== electionId);
        },
        error: (error) => {
          console.error('Error deleting election:', error);
        }
      });
    }
  }
}
