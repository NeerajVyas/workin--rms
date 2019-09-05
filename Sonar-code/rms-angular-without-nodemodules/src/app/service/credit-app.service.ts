
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CreditApp } from 'src/app/model/credit-app';
import Links from './../links.module'
import { BorrowerCreditScore } from '../model/borrower-credit-score';
@Injectable({
  providedIn: 'root'
})
export class CreditAppService {
  currentUserEmail: string;
  currentUserName: string;
  currentUserId: string;
  currentBorrowerId:string;

  constructor(private http: HttpClient) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log('Inside View Recied Compnent Ts', currentUser);
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;

  }



  public save(creditApp: CreditApp) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log('Inside View Recied Compnent Ts', currentUser);
    this.currentUserName = currentUser.userName;
    this.currentUserEmail = currentUser.userEmail;
    //    this.currentUserEmail = localStorage.getItem('username')
    this.currentUserId = currentUser.userId;

    console.log("aman: ", creditApp);
    console.log("Lender Id  ??? =", this.currentUserId);
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON)); {
        return this.http.post<CreditApp>(Links.SUBMIT_CREDITAPP_URL + this.currentUserId, creditApp, { headers });
      }
    }
  }



  public getCreditAppRecord(){
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.currentBorrowerId = currentUser.userId;
    const authJSON = localStorage.getItem('auth');
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON)); {
        return this.http.get<BorrowerCreditScore[]>(Links.CREDIT_APPLICATYION_RECORDS+this.currentBorrowerId,{ headers });
      }
    }
  }
}
