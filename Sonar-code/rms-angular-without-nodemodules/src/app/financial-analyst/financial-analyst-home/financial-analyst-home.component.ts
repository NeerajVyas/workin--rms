import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/login-form/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-financial-analyst-home',
  templateUrl: './financial-analyst-home.component.html',
  styleUrls: ['./financial-analyst-home.component.css']
})
export class FinancialAnalystHomeComponent implements OnInit {
  currentUserName: string;
  currentUserEmail: string;

  divViewReceivedApplicationRequests = true;
  divTopBorrowers = false;
  divViewPreviousCreditApps = false;
  constructor(private authService: AuthenticationService,private router:Router) {
    
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_FINANCIAL_ANALYST")) {
      this.router.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;

  }

  ngOnInit() {
  }

  viewReceivedApplicationRequests() {
    this.divViewReceivedApplicationRequests = true;
    this.divTopBorrowers = false;
    this.divViewPreviousCreditApps = false;
  }
  viewTopBorrowers() {
    this.divViewReceivedApplicationRequests = false;
    this.divTopBorrowers = true;
    this.divViewPreviousCreditApps = false;
  }
  viewPreviousCreditApps() {
    this.divViewReceivedApplicationRequests = false;
    this.divTopBorrowers = false;
    this.divViewPreviousCreditApps = true;
  }

}
