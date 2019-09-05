import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import Links from '../links.module';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }


  getActiveLenders() {
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      //var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return this.http.get<any>(Links.ADMIN_ACTIVE_LENDERS_URL, { headers }).pipe(
        map( 
          serverdataResponse => {
            console.log(" --Registeration--")
            console.log(serverdataResponse)
             return serverdataResponse;
          },(response) => response.json()
         )
       );
    }
  }



  getInactiveLenders(){
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      //var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return this.http.get<any>(Links.ADMIN_INACTIVE_LENDERS_URL, { headers }).pipe(
        map( 
          inactiveLenderResponse => {
            console.log(" --Registeration--")
            console.log(inactiveLenderResponse)
             return inactiveLenderResponse;
          },(response) => response.json()
         )
       );
    }
  }

  deactivateLender(lenderId){

    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));

      return this.http.get<any>(Links.ADMIN_DEACTIVATE_LENDERS_URL+lenderId, {headers}).pipe(
        map( 
          deactivateLenderResponse => {
            console.log(deactivateLenderResponse)
             return deactivateLenderResponse;
          },(response) => response.json()
         )
       );
       
    }
  }

  activateLender(lenderId){

    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      //var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return this.http.get<any>(Links.ADMIN_ACTIVATE_LENDERS_URL+lenderId, { headers }).pipe(
        map( 
          activateLenderResponse => {
            console.log(activateLenderResponse)
             return activateLenderResponse;
          },(response) => response.json()
         )
       );
    }
  }
  
  getActiveBorrowers(){
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      //var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return this.http.get<any>(Links.ADMIN_ACTIVE_BORROWERS_URL, { headers }).pipe(
        map( 
          activeBorrowerResponse => {
            console.log(" --Registeration--")
            console.log(activeBorrowerResponse)
             return activeBorrowerResponse;
          },(response) => response.json()
         )
       );
    }

  }

  getInactiveBorrowers(){
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      //var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return this.http.get<any>(Links.ADMIN_INACTIVE_BORROWERS_URL, { headers }).pipe(
        map( 
          inactiveBorrowerResponse => {
            console.log(" --Inactive Borrwers--")
            console.log(inactiveBorrowerResponse)
             return inactiveBorrowerResponse;
          },(response) => response.json()
         )
       );
    }
  }

  activateBorrower(borrowerId){
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      //var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return this.http.get<any>(Links.ADMIN_ACTIVATE_BORROWERS_URL+borrowerId, { headers }).pipe(
        map( 
          activateBorrowerResponse => {
            console.log(activateBorrowerResponse)
             return activateBorrowerResponse;
          },(response) => response.json()
         )
       );
    }
  }

  deactivateBorrower(borrowerId){
    const authJSON = localStorage.getItem('auth');
    console.log(authJSON);
    if (authJSON && authJSON !== '') {
      const headers = new HttpHeaders(JSON.parse(authJSON));
      //var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return this.http.get<any>(Links.ADMIN_DEACTIVATE_BORROWERS_URL+borrowerId, { headers }).pipe(
        map( 
          deactivateBorrowerResponse => {
            console.log(deactivateBorrowerResponse)
             return deactivateBorrowerResponse;
          },(response) => response.json()
         )
       );
    }

  }

    

    
}
