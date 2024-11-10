import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VotingDashboardComponent } from './components/voting-dashboard/voting-dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { CandidateDashboardComponent } from './components/candidate-dashboard/candidate-dashboard.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { FormsModule } from '@angular/forms';
import { AdminCandidateComponent } from './components/admin-candidate/admin-candidate.component';
import { ElectionResultComponent } from './components/election-result/election-result.component';
import { StudentLoginComponent } from './components/student-login/student-login.component';

@NgModule({
  declarations: [
    AppComponent,
    VotingDashboardComponent,
    HomeComponent,
    CandidateDashboardComponent,
    AdminDashboardComponent,
    AdminCandidateComponent,
    ElectionResultComponent,
    StudentLoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
