import { CreditAppService } from './../../service/credit-app.service';
import { PolicyService } from './../../service/policy.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { CreditApp } from 'src/app/model/credit-app';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-credit-application',
  templateUrl: './credit-application.component.html',
  styleUrls: ['./credit-application.component.css']
})
export class CreditApplicationComponent implements OnInit {
  policies: string[];
  userId;
  creditApp: CreditApp;
  creditAppForm: FormGroup;
  policyJson = [];
  public formLabel: any = [];
  policyResponse: [];
  currentUserName: string;
  currentUserEmail: string;
  hintColor='#ff0000';
  disabled = false;


  constructor(private policyService: PolicyService, private routers: Router, private creditAppService: CreditAppService, private router: ActivatedRoute,
    private formBuilder: FormBuilder) {
    this.creditApp = new CreditApp();
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!(currentUser.userRole === "ROLE_BORROWER")) {
      this.routers.navigate(['access-denied']);
    }
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
  }
  ngOnInit() {
    console.log(this.router);
    this.creditAppForm = this.formBuilder.group({
      cmpName: ['', Validators.required],
      policyName: ['', Validators.required]
    });
    this.userId = this.router.queryParams['getValue']()['lender'];
    this.policyService.getPolicies(this.userId).subscribe(response => this.handle(response));
  }
  handle(response) {
    this.policies = response;
  }

  formatData(policies) {
  

  }
  editvalue(index, event, key) {
    let value = event.target.value;
    this.formLabel[key] = value;
    this.formLabel;
    
  }
  onSubmit() {
    this.policyResponse = this.formLabel;
    this.creditApp.policyValues = this.policyResponse;
    this.creditApp.lenderId = this.userId;
   
    for (var i in this.creditApp.policyValues) {
      let json = {};
      const policyId = i;
      const policyValue = this.creditApp.policyValues[i];

      this.policyJson.push({
        "policyId": policyId,
        "policyValue": policyValue
      });

    }
  
    this.creditApp.policyValues = this.policyJson;
    

    this.creditAppService.save(this.creditApp).subscribe(data=>{
      this.routers.navigate(['previous-credit-score']);
    });
   
   

  }

  checkbox(terms){
    
  }

}

  
