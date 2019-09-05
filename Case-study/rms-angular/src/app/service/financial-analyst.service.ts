import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FAReceivedCreditApp } from '../model/fareceived-credit-app';
import { map } from 'rxjs/operators';
import Links from './../links.module';
import { AuthenticationService } from 'src/app/login-form/authentication.service';
import { TopBorrowersComponent } from '../financial-analyst/top-borrowers/top-borrowers.component';
import { TopBorrowers } from '../model/top-borrowers';

@Injectable({
  providedIn: 'root'
})
export class FinancialAnalystService {
  currentUserID: string
  constructor(private httpClient: HttpClient, private authService: AuthenticationService) {
  }

  getHoldCreditApp() {
    //const authJSON =  sessionStorage.getItem('auth');
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.currentUserID = currentUser.userId;


      return this.httpClient.get<FAReceivedCreditApp[]>(Links.FA_Credit_HOLD + this.currentUserID, { headers }).pipe(
        map(
          userData => {
            console.log(" --Inside FA ")
            console.log(userData)

            return userData;
          }, (response) => response.json()
        )
      );
    }
  }

  topBorrowers(){
       //const authJSON =  sessionStorage.getItem('auth');
       const authJSON = localStorage.getItem('auth');
       console.log(authJSON);
       if (authJSON && authJSON !== '') {
         const headers = new HttpHeaders(JSON.parse(authJSON));
         var currentUser = JSON.parse(localStorage.getItem('currentUser'));
         this.currentUserID = currentUser.userId;
   
   
         return this.httpClient.get<TopBorrowers[]>(Links.FA_TOP_BORROWERS_URL + this.currentUserID, { headers }).pipe(
           map(
             userData => {
               console.log(" --Inside FA ")
               console.log(userData)
   
               return userData;
             }, (response) => response.json()
           )
         );
       }

  }

  viewMoreDetails(faId,appId,borrowerEmail){
    console.log("Inside FA Service",faId,appId,borrowerEmail);
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.currentUserID = currentUser.userId;
      return this.httpClient.get<any>(Links.FA_VIEW_MORE_DETAILS_URL+ this.currentUserID +'&appId='+appId + '&borrowerEmail='+borrowerEmail, { headers }).pipe(
        map(
          userData => {
            console.log(" --Inside FA View More Details ")
            console.log(userData)

            return userData;
          }, (response) => response.json()
        )
      );
    }

   
    
  }


  updateCreditApplication(creditAppStatus:String){
    const authJSON = localStorage.getItem('auth');
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.currentUserID = currentUser.userId;
      return this.httpClient.put<String>(Links.base+'financial-analyst/update-credit-application-status?faId='+ this.currentUserID,creditAppStatus,{headers}).pipe(
        map(data=>{return data;}));
    }
  }


  viewPreviousCreditApps(){
    const authJSON = localStorage.getItem('auth');
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.currentUserID = currentUser.userId;
      return this.httpClient.get<any>(Links.FA_PREVIOUS_CREDIT_URL+ this.currentUserID ,{ headers }).pipe(
        map(
          userData => {
            console.log(" --Inside previous cred ")
            console.log(userData)

            return userData;
          }, (response) => response.json()
        )
      );
    }
  }
 
  /*
   return this.http.get<FAReceivedCreditApp []>('http://localhost:8080/rms/financial-analyst/view-credit-app-form?faid=7101');
 }
 */
}
