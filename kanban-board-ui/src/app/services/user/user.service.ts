import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { User } from 'src/app/models/user';
import { ProjectService } from '../project/project.service';
import { Column } from 'src/app/models/column';
import { Project } from 'src/app/models/project';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  isLoggedIn = new BehaviorSubject<boolean>((localStorage.getItem('isLoggedIn') == "true") ?? false);
  authToken = new BehaviorSubject<string>(localStorage.getItem("jwt") ?? '');
  columns = new BehaviorSubject<Column[]>([]);
  projects = new BehaviorSubject<Project[]>([]);
  members = new BehaviorSubject<User[]>([]);


  appUrl:string = "http://localhost:9999/api"
  loginBaseUrl:string="http://localhost:9999/authentication/login"
  constructor(private http:HttpClient) { }

  registerUser(registerData:User){
    return this.http.post(`${this.appUrl}/register`,registerData);
  }
  
  loginUser(logindata:User){
    return this.http.post(this.loginBaseUrl,logindata);
  }

  logout():void {
    this.isLoggedIn.next(false);
    this.projects.next([]);
    this.columns.next([]);
    localStorage.clear();
  }

  getAllUserByIds(ids:string[]):Observable<any> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken.value
    })
    return this.http.post(`${this.appUrl}/auth/get-users`,ids, {
      headers : httpHeaders
    })
  }

}
