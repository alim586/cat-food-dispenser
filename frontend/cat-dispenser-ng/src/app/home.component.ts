import {Component} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {printLine} from "tslint/lib/verify/lines";

@Component({
  selector: 'app-root',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  title = 'Arduino Project: Cat food dispenser'

  constructor(private http: HttpClient) {
  }


  public getArduinoResponse() {
    let httpHeaders = new HttpHeaders();
    httpHeaders.set('Content-Type', 'application/json; charset=utf-8');
    httpHeaders.set('Access-Control-Allow-Origin', "*");


    this.http.get('http://localhost:8080/pin/pin_13?state=9', {headers: httpHeaders}).subscribe(data => {

      printLine(data);
      // Read the result field from the JSON response.
      data['pinValue'];
    });
  }


}
