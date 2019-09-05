import { Component, OnInit } from '@angular/core';
import { LenderService } from 'src/app/service/lender.service';
import { Policy } from 'src/app/model/policy';
import { MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition, MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-policy',
  templateUrl: './add-policy.component.html',
  styleUrls: ['./add-policy.component.css']
})

export class AddPolicyComponent implements OnInit {
  checked: boolean;
  policy: string[];
  policy2: string[];
  policyJson = [];
  updatedPolicy: Policy[];
  flag = false;
  show:boolean;
  currentUserName: string;
  currentUserEmail: string;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private lenderService: LenderService,private snackBar: MatSnackBar,private router : Router) { 
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_LENDER")) {
      this.router.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
  }
  
  ngOnInit() {

    this.lenderService.viewPolicy().subscribe(

      response => this.handleSuccessfulResponse(response)

    );

  }

  handleSuccessfulResponse(response) {

    this.policy = response;

    

    this.policy.forEach(element => {

      if (element['policyStatus'] == '1') {

        element['policyStatus'] = true;
       // this.show=true;

      } else {

        element['policyStatus'] = false;
      //  this.show=false;
      }

    });



  }

  onSubmit(event) {

    console.log("Length of policy ", this.policy.length);

    // console.log("Length",event.target.elements);

    // console.log(this.policy[0].policyStatus);

    // console.log(event.target.elements[0].value)

    // console.log(event.target.elements[1].value)

    // console.log(event.target.elements[2].value)

    // console.log(event.target.elements[3].value)

    // console.log(event.target.elements[4].value)

    // console.log(event.target.elements[5].value)

    // console.log(event.target.elements[6].value)

    // console.log(event.target.elements[7].value)

    var loop = this.policy.length * 4;

    var i = 0;

    let json = {};

    this.policyJson = [];

    console.log("Toggle", event.target.elements[3].checked)

    while (i !== loop) {

      // console.log(i);

      if (event.target.elements[i + 3].checked === true) {

        this.policyJson.push({ "policyId": event.target.elements[i].value, "threshold": event.target.elements[i + 1].value, "policyWeightage": event.target.elements[i + 2].value, "policyStatus": 1 });

        console.log(this.policyJson);

      } else {

        this.policyJson.push({ "policyId": event.target.elements[i].value, "threshold": event.target.elements[i + 1].value, "policyWeightage": event.target.elements[i + 2].value, "policyStatus": 0 });

        console.log(this.policyJson);


      }

      i = i + 4;

    }

    this.updatedPolicy = this.policyJson;

    console.log("Final", this.updatedPolicy);

    this.lenderService.updatePolicy(this.updatedPolicy).subscribe(data=>{
      this.openSnackBar('Policy Updated ', 'Successfully');
    });
  }


  openSnackBar(message, status) {
    this.snackBar.open(message, status, {
      duration: 2500,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
      panelClass: ['blue-snackbar']
    });

  }


  onChange(event, policyId) {

    console.log("Event", event.checked);

    console.log("pid", policyId);


  }

}



