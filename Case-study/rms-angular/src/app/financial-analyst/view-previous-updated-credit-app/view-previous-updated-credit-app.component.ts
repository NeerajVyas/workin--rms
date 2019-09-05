import { Component, OnInit, ViewChild } from '@angular/core';
import { FinancialAnalystService } from 'src/app/service/financial-analyst.service';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-previous-updated-credit-app',
  templateUrl: './view-previous-updated-credit-app.component.html',
  styleUrls: ['./view-previous-updated-credit-app.component.css']
})
export class ViewPreviousUpdatedCreditAppComponent implements OnInit {

  previousCreditApp: any [];
  previousCreditAppColumns = ['applicationId', 'creditScore', 'applicationStatus', 'companyName','creationDate'];
  currentUserName:string;
  currentUserEmail:string;
  previousCreditAppSource: MatTableDataSource<any>;
  constructor(private financialanalyst:FinancialAnalystService,private router : Router ) {
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      if (!(currentUser.userRole === "ROLE_FINANCIAL_ANALYST")) {
        this.router.navigate(['access-denied']);
      }
      this.currentUserName = currentUser.userName;
      this.currentUserEmail = currentUser.userEmail;
  }


  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  ngOnInit() { 
    this.financialanalyst.viewPreviousCreditApps().subscribe(
    response => {
      if (response !== [] && response) {
        this.previousCreditApp = response;
        this.previousCreditAppSource = new MatTableDataSource(this.previousCreditApp);
        this.previousCreditAppSource.sort = this.sort;
        this.previousCreditAppSource.paginator = this.paginator;
      }
    }, error => {
      this.previousCreditApp = []
    }
  );
  }

  applyFilter(filterValue: string) {
    this.previousCreditAppSource.filter = filterValue.trim().toLowerCase();
  }

}
