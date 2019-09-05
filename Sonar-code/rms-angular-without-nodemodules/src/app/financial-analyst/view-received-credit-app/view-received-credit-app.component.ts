import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { FinancialAnalystService } from 'src/app/service/financial-analyst.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { AuthenticationService } from 'src/app/login-form/authentication.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { ViewDetailsDialougComponent } from './view-details-dialoug/view-details-dialoug.component';

@Component({
  selector: 'app-view-received-credit-app',
  templateUrl: './view-received-credit-app.component.html',
  styleUrls: ['./view-received-credit-app.component.css']
})
export class ViewReceivedCreditAppComponent implements OnInit {
  // 
  /*  {
        "applicationNumber": "APP5101",
        "borrowerName": "Deepak",
        "borrowerEmail": "deepak@gmail.com",
        "companyName": "Impetus",
        "creditScore": 50
    }

    1. Create columns
    2. Data Source  , model (interface)
        creditAppDataSource : MatTableDataSource<CreditAppModel>;
    3. variable for array
    4. response entry in above variable/
    5. pass it into datasource. 
          this.creditAppDataSource = new MatTableDataSource (this.creditApps);

          Now go in html.
    */

  creditAppDisplayedColumns = ['applicationNumber', 'borrowerEmail', 'borrowerName', 'companyName', 'creditScore', 'view'];
  creditAppDataSource: MatTableDataSource<any>;
  creditApps: any;
  viewMoreDetailsResponse : any;

  currentUserEmail: string;
  currentUserName: string;
  constructor(
    private authService: AuthenticationService,
    private financialAnalystService: FinancialAnalystService, public dialog: MatDialog,
    private router: Router) {

      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      if (!(currentUser.userRole === "ROLE_FINANCIAL_ANALYST")) {
        this.router.navigate(['access-denied']);
      }
      console.log('Inside View Recied Compnent Ts', currentUser);
      this.currentUserName = currentUser.userName;
      this.currentUserEmail = currentUser.userEmail;
    
    
    
      console.log('dialog=parent');

    /*
    authService.currentUser.subscribe(user => {
      console.log('user,..', user);
      this.currentUserName = user.userName;
      this.currentUserEmail= user.userEmail;
      
    })
    */
    
    // this.currentUserEmail = localStorage.getItem('username')

  }

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  ngOnInit() {



    this.financialAnalystService.getHoldCreditApp().subscribe(
      response => {
        console.log("Inside View Recieved", response);
        if (response !== [] && response) {
          this.creditApps = response;
          this.creditAppDataSource = new MatTableDataSource(this.creditApps);
          console.log("Inside View Recieved", JSON.stringify(this.creditAppDataSource.data));
          this.creditAppDataSource.sort = this.sort;
          this.creditAppDataSource.paginator = this.paginator;
        }
      }, error => {
        this.creditApps = []
      }
    );


  }

  viewAppCredDetails(credApp) {
    console.log(credApp);
  }


  /*
   @ViewChild(MatSort, {static: true}) sort: MatSort;
   inside ngOnInIt()
       this.creditAppDataSource.sort = this.sort;
             
  */
  applyFilter(filterValue: string) {
    this.creditAppDataSource.filter = filterValue.trim().toLowerCase();
  }


  openDialog(application) {
    console.log('app = ',application.applicationNumber)
    console.log(application.borrowerEmail)
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log('Inside Open Dialog', currentUser.userId);

    this.financialAnalystService.viewMoreDetails(currentUser.userId,application.applicationNumber,application.borrowerEmail).subscribe(
      response => {
        console.log("Inside View Recieved", response);
        if (response !== [] && response) {
          this.viewMoreDetailsResponse=response;
          console.log('Response',this.viewMoreDetailsResponse);
          const dialogRef = this.dialog.open(ViewDetailsDialougComponent,{data: {
            viewDetails : this.viewMoreDetailsResponse 
          }});
          
          
          dialogRef.afterClosed().subscribe(result => {
            console.log(`Dialog result: ${result}`);
            this.ngOnInit();
          });
          
        }
      }, error => {
        this.viewMoreDetailsResponse = {}
      }
    );
 
  }



 

}



/*
export interface DialogData {
  animal: 'panda' | 'unicorn' | 'lion';
}
*/



