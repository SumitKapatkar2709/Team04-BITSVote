import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  constructor(private router: Router) {}

  onStudentLogin() {
    // Navigate to the voting dashboard
    this.router.navigate(['/student-login']);
  }

  onAdminLogin() {
    this.router.navigate(['/admin-dashboard']);  }
}
