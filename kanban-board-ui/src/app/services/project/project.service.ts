import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Column } from 'src/app/models/column';
import { Project } from 'src/app/models/project';
import { UserService } from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  baseUrl: string = "http://localhost:9999/api/auth/project";
  authToken:string = '';


  constructor(private http: HttpClient, private userService:UserService) { 
    this.userService.authToken.subscribe( data => this.authToken = data);
  }

  getProjectById(id: String): Observable<any> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken
    })

    return this.http.get(`${this.baseUrl}/get/${id}`, {
      headers: httpHeaders
    })
  }

  getProjectsByUser() : Observable<any> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken     
    })
    let requestOptions = { headers: httpHeaders }
    return this.http.get(this.baseUrl + "/get-by-user", requestOptions);
  }

  addColumn(projectId: String, column: Column): Observable<any> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken
    })

    return this.http.patch(`${this.baseUrl}/add-column/${projectId}`, column, {
      headers: httpHeaders
    })

  }

  deleteColumn(projectId: string, columnId: String): Observable<any> {
    console.log("In Service Page", columnId);

    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken
    })

    return this.http.patch(`${this.baseUrl}/delete-column/${projectId}/${columnId}`, '', {
      headers: httpHeaders
    })
  }

  updateColumnWithTask(projectId: String, columns: Column[] | undefined): Observable<any> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken
    })
    // console.log("Columns In Service Page", columns);

    return this.http.patch(`${this.baseUrl}/edit-column/${projectId}`, columns, {
      headers: httpHeaders
    })
  }

  updateColumnDetails(projectId: string, column: Column): Observable<any> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken
    })
    console.log("Column Details In Service Page", column);

    return this.http.patch(`${this.baseUrl}/edit-column-details/${projectId}`, column, {
      headers: httpHeaders
    })
  }

  createProject(projectDetails: Project) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken  
    })
    let requestOptions = { headers: httpHeaders }
    return this.http.post(this.baseUrl + "/add", projectDetails, requestOptions);

  }


  addMember(email:string,projectId:string){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken     
    })
    let requestOptions = { headers: httpHeaders }
    return this.http.patch(`${this.baseUrl}/assign-member/${email}/${projectId}`,{},requestOptions);
  }

  editProject(project:Project){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken  
    })
    let requestOptions = { headers: httpHeaders }
    return this.http.put(this.baseUrl + "/edit", project, requestOptions);

  }

  deleteProject(projectId:string){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken     
    })
    let requestOptions = { headers: httpHeaders }
    return this.http.delete(`${this.baseUrl}/delete/${projectId}`,requestOptions);
  }

}
