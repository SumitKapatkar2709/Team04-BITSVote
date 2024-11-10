import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'bitsvote';
  constructor(private router: Router) {}

  onStudentLogin() {
    // Navigate to the voting dashboard
    this.router.navigate(['/student-login']);
  }

  onAdminLogin() {
    this.router.navigate(['/admin-dashboard']);  
  }
}
