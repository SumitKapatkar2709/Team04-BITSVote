import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-student-login',
  templateUrl: './student-login.component.html',
  styleUrls: ['./student-login.component.scss']
})
export class StudentLoginComponent implements OnInit {
  studentId: number | null = null;
  hostelOptions = ['Bhagirath Bhavan', 'Gandhi Bhavan','Mira Bhavan','Malvia Bhavan', 'Budh Bhavan', 'Ashok Bhavan','Ranapratap Bhavan','Shankar Bhavan','Vishwakarma Bhavan', 'Ram Bhavan' ];
  selectedHostel = '';
  hostel = '';

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    // Fetch the authenticated student’s information
    this.http.get<{ studentId: number, studentHostel: string }>(`/api/students/me`).subscribe(
      (student) => {
        this.studentId = student.studentId;
        
        // If student already has a hostel assigned, redirect to election dashboard
        if (student.studentHostel) {
          this.hostel = student.studentHostel;
          this.router.navigate(['/voting-dashboard']);
        }
      },
      (error) => {
        console.error('Failed to fetch student information', error);
      }
    );
  }

  // Update hostel for the authenticated student
  updateHostel() {
    if (this.hostel) {
      this.router.navigate(['/voting-dashboard']);
    }else{
    if (this.selectedHostel) {
      const requestBody = {
        studentId: this.studentId,
        hostel: this.selectedHostel
      };

      const url = `${environment.apiBaseUrl}/elections/update-hostel`; // API URL from environment
      this.http.post(url, requestBody).subscribe(
        () => {
          // After updating, redirect to voting dashboard
          this.router.navigate(['/voting-dashboard']);
        },
        (error) => {
          console.error('Failed to update hostel', error);
        }
      );
    }
  }
  }
}
