import { Token } from '@angular/compiler';
import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../models/user';
import { UserService } from '../services/user/user.service';
import { Route, Router, RouterModule } from '@angular/router';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  imageUrl: string = "/src/assets/images/LoginPage1.jpg";

  loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.pattern(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i)]],
    password: ['', Validators.required]
  });
  temp: any;

  constructor(private fb: FormBuilder, private _snackBar: MatSnackBar, private userService: UserService, private router: Router) { }

  get email() { return this.loginForm.get("email") }
  get password() { return this.loginForm.get("password") }

  ngOnInit(): void {
    
  }
  responseData: any;

  onSubmit() {
    this.temp = this.loginForm.value;
    // console.log(this.temp);
    this.userService.loginUser(this.temp).subscribe({
      next : (response) => {
        this.responseData = response;
       
        this.userService.authToken.next(this.responseData.token);
        localStorage.setItem("jwt", this.responseData.token);
        localStorage.setItem("isLoggedIn", 'true');
        this.userService.isLoggedIn.next(true);
        this.router.navigateByUrl('');
      },
      error : (err) => {
        let error;
        if(err.status == 404 ) {
          error = "Login Failed! Please Check The Credentials Again!"
        }else {
          error = "Could not Login! Please Try After Some Time"
        }
        this._snackBar.open(error,"Close", {
          duration : 3000
        })
      }
    },


    );
  }


}
