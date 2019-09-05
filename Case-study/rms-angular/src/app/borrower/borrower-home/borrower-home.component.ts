import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-borrower-home',
  templateUrl: './borrower-home.component.html',
  styleUrls: ['./borrower-home.component.css']
})
export class BorrowerHomeComponent implements OnInit {
  checkCreditScore = false;
  previousCreditScore = false;
  viewLender=true;
  currentUserName : string;
  currentUserEmail : string;
  
  constructor(private router : Router) { 
     var currentUser = JSON.parse(localStorage.getItem('currentUser'));
   
    if (!(currentUser.userRole === "ROLE_BORROWER")) {
      this.router.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail; 
}

  ngOnInit() {
  }
  checkCreditScores() {
    this.checkCreditScore = true;
    this.viewLender=false;
    this.previousCreditScore = false;
  }
  previousCreditScores(){
    this.previousCreditScore=true;
    this.viewLender=false;
    this.checkCreditScore=false;
  }
  viewLenders(){
    this.viewLender=true;
    this.previousCreditScore=false;
    this.checkCreditScore=false;
  }
}
