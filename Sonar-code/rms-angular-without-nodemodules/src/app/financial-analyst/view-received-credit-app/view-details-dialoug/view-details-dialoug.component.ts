import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatTableDataSource } from '@angular/material';
import { FinancialAnalystService } from 'src/app/service/financial-analyst.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-view-details-dialoug',
  templateUrl: './view-details-dialoug.component.html',
  styleUrls: ['./view-details-dialoug.component.css']
})
export class ViewDetailsDialougComponent {

  dataResponse : any;
   policyId : string [];
    status : any;
    selected = 'Approve';
    policy='';
    updateddata:any;
    datasource = [];
     currentCreditScore =[];
  constructor (private router : Router,private financialAnalystService:FinancialAnalystService,public dialogRef: MatDialogRef<ViewDetailsDialougComponent> ,@Inject(MAT_DIALOG_DATA) public data: any){
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_FINANCIAL_ANALYST")) {
      this.router.navigate(['access-denied']);
    }
    
    console.log('Inside COnstructor',data.viewDetails);
    this.dataResponse = data.viewDetails;
    let pid=[];
    let policyName=[];
    let threshold=[];
    let valueborrower=[];
    let weightage=[];
     
     pid = this.dataResponse['policyId'];
    policyName=this.dataResponse['policyNamebyLender'];
    threshold=this.dataResponse['policyThresholdbyLender'];
    valueborrower=this.dataResponse['policyValuebyBorrower'];
    weightage=this.dataResponse['policyWeightagebyLender']; 


    

console.log(pid);

let datajson={};
var totalScore =0;

     for(var i = 0 ; i < this.dataResponse.policyId.length ; i++){
   /*     console.log('Policy Id',this.dataResponse.policyId[i]);
       console.log('Policy Name',this.dataResponse.policyNamebyLender[i]);
       console.log('Policy Value',this.dataResponse.policyValuebyBorrower[i]);
       console.log('Policy Thershold',this.dataResponse.policyThresholdbyLender[i]);
       console.log('Policy Weightage',this.dataResponse.policyWeightagebyLender[i]); */
       let datajson={};
       datajson['policyId']=pid[i];
       datajson['policyName']=policyName[i];
       datajson['threshold']=threshold[i];
       datajson['valueborrower']=valueborrower[i];
       datajson['weighage']=weightage[i];
          var policyValue = +valueborrower[i];
          var policyThreshold = + threshold[i];
          var policyWeightage=+weightage[i];

          //

          this.calculateCreditScore(totalScore, policyThreshold, policyWeightage,policyValue,i);
          datajson['currentCreditScore']=this.currentCreditScore[i];
      this.datasource.push(datajson);
  
     }
     console.log(this.datasource);
  //   console.log("data json",datajson);

     /* 
     for(var i = 0 ; i < this.dataResponse.policyId.length ; i++){
      console.log(this.dataResponse.policyValuebyBorrower[i]);
    }

 this.dataResponse.policyId.array.forEach( (element,index) => {

      let datajson={};
      datajson['policyId']=pid[index];
      datajson['policyName']=policyName[index];
      datajson['threshold']=threshold[index];
      datajson['valueborrower']=valueborrower[index];
      datajson['weighage']=weightage[index];
      console.log("data json",datajson);
       
     });


 */
    // this.policyId = data.viewDetails.policyId;
    
  }

  close(){
   this.dialogRef.close('closed');
  }

  onSelect(event){
    console.log('Hello');
    console.log(event.value);
    this.status=event.value;
  }

  updateCreditStatus(event){
    console.log('App Status = '+this.selected);
    console.log('App ID = '+event.target.elements[0].value);
    console.log('Borrower Email = ',event.target.elements[1].value);
    console.log('Lender Email = '+event.target.elements[2].value);

   var updateCreditAppStatus = 
                     {
                         "applicationStatus": this.selected,
                         "applicationId": event.target.elements[0].value,
                         "borrowerEmail":event.target.elements[1].value,
                         "lenderEmail":event.target.elements[2].value
                     }; 
    this.updateddata=updateCreditAppStatus
    console.log(updateCreditAppStatus);
    this.financialAnalystService.updateCreditApplication(this.updateddata).subscribe();
    this.dialogRef.close('closed');
  }

 /*   public static int calculateCreditScore(int creditScore,int policyThreshold,int policyWeightage,long policyValue) {
        
        if(policyValue>=policyThreshold) {
            creditScore = creditScore + policyWeightage;
        }else {
            creditScore = (int) (creditScore + (policyValue/policyThreshold)*policyWeightage);
        }
        
        return creditScore;
    }


 */

 calculateCreditScore( creditScore, policyThreshold, policyWeightage, policyValue,i){
  
  if(policyValue>=policyThreshold) {
    creditScore = creditScore + policyWeightage;
    }else {
    creditScore = (creditScore + (policyValue/policyThreshold)*policyWeightage);
    }

    this.currentCreditScore[i]=creditScore.toFixed(2);
    console.log(this.currentCreditScore[i],'Values ------>', creditScore.toFixed(2), policyThreshold, policyWeightage, policyValue);
 }


}