import { Injectable } from '@angular/core';

import { HttpClient,HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
import Links from '../links.module';


export class User{
  constructor(
    public userId:string,
    public userEmail:string,
    public userRole:string,
    public userName:string,
    public userAIStatus:string
     ) {}
}


/**
 *  For passing data from one componenet to other component 
 * 
 * 1. import { BehaviorSubject } from 'rxjs';
 *  
 * Inside Sevice 
 * 2.   private user = new BehaviorSubject<User>(null);
        currentUser = this.user.asObservable();

 * 3. Inside Service , Inside get Http 
          this.updateCurrentUser(userData);


          wherever you want to use it, Inside the constructor.

          

           constructor(
              private authService: AuthenticationService) {

                authService.currentUser.subscribe(user => {
                  console.log('user,..', user);
                  
                })
               }
  
 */

@Injectable()
export class AuthenticationService {

  private user = new BehaviorSubject<User>(null);
  currentUser = this.user.asObservable();
  private tokenGenerated : any;
  constructor(private httpClient:HttpClient) { }

  authenticate(username, password) {
   // this.authenticateUsingJwt(username,password);
  //const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
  console.log("authenticate",username);

  const authJSON = localStorage.getItem('auth');
  console.log(authJSON);
   const headers = new HttpHeaders(JSON.parse(authJSON));
   /*
   return this.httpClient.post<any>('http://localhost:8080/authenticate',{username,password}).pipe(
    map( tokenData => {
      console.log('called',tokenData)
      //this.tokenGenerated=tokenData;
  //    localStorage.setItem('username','username')
      localStorage.setItem('auth', JSON.stringify({ Authorization: 'Bearer' + tokenData }));
      return tokenData;
    }
      )
    );
*/

 return this.httpClient.get<User>(Links.base+'login/validateLogin?emailId='+username+'',{headers}).pipe(
  map( 
    userData => {
      console.log(" --after ")
      console.log(userData)
      this.updateCurrentUser(userData);

   //  sessionStorage.setItem('username',username);
   //  sessionStorage.setItem('auth', JSON.stringify({ Authorization: 'Basic ' + btoa(username + ':' + password) }));
     localStorage.setItem('username',username);
   //  localStorage.setItem('auth', JSON.stringify({ Authorization: 'Basic ' + btoa(username + ':' + password) }));
     localStorage.setItem('currentUser', JSON.stringify(userData));
     return userData;
    },   (response) => response.json()
   )
 );
 
  }

  authenticateUsingJwt(username,password){
    //Bearer
    var credentials = {
      "username" : username,
      "password" : password
    };

    console.log("authenticateJWT",credentials);
    console.log(credentials);
    return this.httpClient.post<any>(Links.baseAuth,{username,password}).pipe(
      map( tokenData => {
        console.log('called',tokenData)
        //this.tokenGenerated=tokenData;
    //    localStorage.setItem('username','username')
        localStorage.setItem('auth', JSON.stringify({ Authorization: 'Bearer ' + tokenData.token }));
        return tokenData;
      }
        )
      );

  }



  updateCurrentUser(user){
    this.user.next(user);
    console.log('Value of currebt user updated', user);
    
  }


  isUserLoggedIn() {
//    let user = sessionStorage.getItem('username')
    let user = localStorage.getItem('username')

    console.log(user)
    console.log("InsideUserLoggedIn")
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    //sessionStorage.removeItem('username')
    //sessionStorage.removeItem('username')
    localStorage.removeItem('username')
    localStorage.removeItem('auth')
    localStorage.clear();
  
  }
}