import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-app-footer',
  templateUrl: './app-footer.component.html',
  styleUrls: ['./app-footer.component.css']
})
export class AppFooterComponent implements OnInit {
  year : string;
  constructor() { }

  ngOnInit() {
   let dt =new Date();
 //  console.log("Date ", dt.getFullYear());
   this.year=""+dt.getFullYear();
  }

}
