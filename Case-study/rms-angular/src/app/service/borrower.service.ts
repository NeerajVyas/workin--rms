import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Borrower } from '../model/borrower';
import { map } from 'rxjs/operators';
import { ServerResponse } from '../model/server-response';
import { BehaviorSubject } from 'rxjs';
import Links from '../links.module';
@Injectable({
  providedIn: 'root'
})
export class BorrowerService {

  private serverResponse = new BehaviorSubject<ServerResponse>(null);
  currentUser = this.serverResponse.asObservable();

  private registerUrl:string;
  constructor(private http: HttpClient) {
    this.registerUrl=Links.base+'registerborrower';
  }

  public save(borrower:Borrower){
    return this.http.post<Borrower>(this.registerUrl,borrower).pipe(
      map( 
        serverdataResponse => {
          console.log(" --Registeration--")
          console.log(serverdataResponse)
          this.updateServerResopnse(serverdataResponse);
       //  sessionStorage.setItem('username',username);
       //  sessionStorage.setItem('auth', JSON.stringify({ Authorization: 'Basic ' + btoa(username + ':' + password) }));
       //  localStorage.setItem('username',username);
       //  localStorage.setItem('auth', JSON.stringify({ Authorization: 'Basic ' + btoa(username + ':' + password) }));
        // localStorage.setItem('currentUser', JSON.stringify(userData));
         return serverdataResponse;
        },(response) => response.json()
       )
     );
  }

  updateServerResopnse(serverResponse){
    this.serverResponse.next(serverResponse);
    console.log('Status',serverResponse.status);
    console.log('message =',serverResponse.message);
    console.log('Value of current server resopnse updated', serverResponse);   
  }


}
