import { Component, OnInit, ViewChild } from '@angular/core';
import { LenderService } from 'src/app/service/lender.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-financial-analyst',
  templateUrl: './view-financial-analyst.component.html',
  styleUrls: ['./view-financial-analyst.component.css']
})
export class ViewFinancialAnalystComponent implements OnInit {
  financialanalyst:any;
  currentUserName: string;
  currentUserEmail: string;
  constructor(private lenderService:LenderService,private router:Router) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_LENDER")) {
      this.router.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
   }
  FAColumns = ['financialAnalystId', 'financialAnalystName','financialAnalystEmail', 'pendingRequests'];
  FADataSource: MatTableDataSource<any>;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  ngOnInit() {
    this.lenderService.viewFA().subscribe(
      response => {
        console.log("Inside fa", response);
        if (response !== [] && response) {
          this.financialanalyst = response;
          this.FADataSource = new MatTableDataSource(this.financialanalyst);
          console.log("Inside fa", JSON.stringify(this.FADataSource.data));
          this.FADataSource.sort = this.sort;
          this.FADataSource.paginator = this.paginator;


      }
      });
   // this.lenderService.viewFA().subscribe(response=>this.handleSuccessfulResponse(response));
  }



  applyFilter(filterValue: string) {
    this.FADataSource.filter = filterValue.trim().toLowerCase();
  }



}


/* import { Component, OnInit } from '@angular/core';
import { LenderService } from 'src/app/service/lender.service';

@Component({
  selector: 'app-view-financial-analyst',
  templateUrl: './view-financial-analyst.component.html',
  styleUrls: ['./view-financial-analyst.component.css']
})
export class ViewFinancialAnalystComponent implements OnInit {
  financialanalyst:string[];
  constructor(private lenderService:LenderService) { }

  ngOnInit() {
    this.lenderService.viewFA().subscribe(response=>this.handleSuccessfulResponse(response));
  }
  handleSuccessfulResponse(response){
    this.financialanalyst=response;
  }
    
}
 */