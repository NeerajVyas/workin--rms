import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../login-form/authentication.service';
import { Router, ActivatedRoute } from '@angular/router';
//import 'rxjs/add/operator/filter';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {
  divAdminHome = true;
  divViewLenders = false;
  divViewBorrowers = false;
  divViewFinancialAnalyst = false;

  currentUserName: string;
  currentUserEmail: string;
  constructor(private router: Router) {

    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_ADMIN")) {
      this.router.navigate(['access-denied']);
    }
    console.log('Inside Borrower Home - ', currentUser);
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
  }

  ngOnInit() {

  }
}