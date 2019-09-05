import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-financial-analyst-sidebar',
  templateUrl: './financial-analyst-sidebar.component.html',
  styleUrls: ['./financial-analyst-sidebar.component.css']
})
export class FinancialAnalystSidebarComponent implements OnInit {

  constructor(private router : Router) { 
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_FINANCIAL_ANALYST")) {
      this.router.navigate(['access-denied']);
    }
  }

  ngOnInit() {
  }

  
}
