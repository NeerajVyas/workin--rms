import { CreditAppService } from './../../service/credit-app.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { LenderService } from 'src/app/service/lender.service';
import { ViewDetailsBorrowerDialogComponentComponent } from './view-details-borrower-dialog-component/view-details-borrower-dialog-component.component';

@Component({
  selector: 'app-previous-credit-score',
  templateUrl: './previous-credit-score.component.html',
  styleUrls: ['./previous-credit-score.component.css']
})
export class PreviousCreditScoreComponent implements OnInit {
  creditRecords:any;
  currentUserName : string;
  currentUserEmail : string;
  currentBorrowerId:string;
  creditAppColumns = ['applicationId', 'companyName', 'creditScore', 'applicationStatus','lenderName', 'creationDate','viewDetails'];
  creditAppDataSource: MatTableDataSource<any>;
  dataisThere : boolean;
 
  currentCreditScore:string;
  currentAppId:string;
 currentAppStatus:string;
 currentLenderName:string;
 currentCompanyName:string;
 viewMoreDetailsResponse:any;

  constructor(private creditAppService: CreditAppService,private router : Router,private lenderService : LenderService,public dialog: MatDialog) { 
   
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_BORROWER")) {
      this.router.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail; 
    this.currentBorrowerId=currentUser.userId;
    this.dataisThere=false;
  }

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  ngOnInit() {
    this.creditAppService.getCreditAppRecord().subscribe(
      response => {
        if (response !== [] && response) {
          this.creditRecords = response;
          this.creditAppDataSource = new MatTableDataSource(this.creditRecords);
          this.creditAppDataSource.sort = this.sort;
          this.creditAppDataSource.paginator = this.paginator;
          if(this.creditRecords.length > 0){
            this.dataisThere = true;
          }
          this.currentAppId=this.creditRecords[0].applicationId;
          this.currentCreditScore=this.creditRecords[0].creditScore;
          this.currentAppStatus=this.creditRecords[0].applicationStatus;
          this.currentCompanyName=this.creditRecords[0].companyName;
          this.currentLenderName=this.creditRecords[0].lenderName;
                }

      }, error => {
        this.creditRecords = []
        
        if(this.creditRecords.length === 0){
          this.dataisThere = false;
        }
      });
       
    }
    openDialog(element){
      
  
      this.lenderService.viewMoreDetails(element.applicationId).subscribe(
        response => {
         
          if (response !== null && response) {
            this.viewMoreDetailsResponse=response;
            
            const dialogRef = this.dialog.open(ViewDetailsBorrowerDialogComponentComponent,{data: {
              viewDetails : this.viewMoreDetailsResponse 
            }});
            
            
            dialogRef.afterClosed().subscribe(result => {
             
            });
        } 
        }, error => {
          //this.viewMoreDetailsResponse = {}
        }
      );
  
     
    }

    applyFilter(filterValue: string) {
      this.creditAppDataSource.filter = filterValue.trim().toLowerCase();
    }


}

