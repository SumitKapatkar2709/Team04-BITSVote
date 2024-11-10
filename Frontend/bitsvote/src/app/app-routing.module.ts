import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component'; // Landing page with login buttons
import { VotingDashboardComponent } from './components/voting-dashboard/voting-dashboard.component'; // Dashboard for student voters
// import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component'; // Dashboard for admin (if applicable)
import { CandidateDashboardComponent } from './components/candidate-dashboard/candidate-dashboard.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminCandidateComponent } from './components/admin-candidate/admin-candidate.component';
import { ElectionResultComponent } from './components/election-result/election-result.component';
import { StudentLoginComponent } from './components/student-login/student-login.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },  // Default route to Home page
  { path: 'home', component: HomeComponent },            // Welcome / Home page
  { path: 'voting-dashboard', component: VotingDashboardComponent }, // Voting dashboard page for students
  { path: 'elections/:electionId/candidates', component: CandidateDashboardComponent },
  { path: 'elections', component: VotingDashboardComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent},
  { path: 'admin-candidate/:electionId', component: AdminCandidateComponent },
  { path: 'elections/:id/results', component: ElectionResultComponent },
  { path: 'results', component: ElectionResultComponent },
  { path: 'student-login', component: StudentLoginComponent },
  { path: '**', redirectTo: '/home' }     // Wildcard route (if no path matches, redirect to home)
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
