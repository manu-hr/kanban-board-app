import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from '../user/user.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EditProfileService {
  baseUrl1="http://localhost:9999/api/auth";
  authToken:string = '';

  constructor(private http:HttpClient, private userService:UserService) {
    this.userService.authToken.subscribe( data => this.authToken = data);
   }

  viewProfile()
  {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken
    })
    console.log();
    
    let requestOptions={headers:httpHeaders}
    return this.http.get(this.baseUrl1+"/getUserDetail",requestOptions);
  }

  updateProfileDetails(userDetails:User)
  {
    
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken
    })
    console.log();
    
    let requestOptions={headers:httpHeaders}
    return this.http.put(this.baseUrl1+"/updateProfile",userDetails,requestOptions);

  }

  uploadProfilePic(profilePic:any): Observable<any> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + this.authToken
    })
    return this.http.post(`${this.baseUrl1}/upload-pic`,profilePic, {
      headers : httpHeaders
    })
  }


}
