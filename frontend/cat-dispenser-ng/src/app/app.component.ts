import {Component} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {printLine} from "tslint/lib/verify/lines";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Arduino Project: Cat food dispenser'

}
