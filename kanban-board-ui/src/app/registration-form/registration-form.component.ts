import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Task } from '../models/task';
import { User } from '../models/user';
import { UserService } from '../services/user/user.service';
import { Router } from '@angular/router';
import { ErrorDialogueComponent } from '../error-dialogue/error-dialogue.component';
import { MatDialog } from '@angular/material/dialog';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {

  constructor(private fb: FormBuilder, 
    private _snackBar: MatSnackBar, 
    private userService: 
    UserService, 
    private router: Router,
    private dialog:MatDialog
    ) { }

  ngOnInit(): void {
  }

  registrationForm = this.fb.group
    ({
      userName: ['', Validators.required],
      email: ['', [Validators.required, Validators.pattern(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i)]],
      phoneNumber: ['', [Validators.required, Validators.pattern("^[6-9][0-9]{9}$")]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validators: [this.mustMatchValidator] });

  // tempData : User;
  responseData: any;
  temp: any;
  
  sendRegisterData() {
    console.log(this.registrationForm.value);
    this.temp = this.registrationForm.value;
    delete this.temp.confirmPassword;
    this.userService.registerUser(this.temp).subscribe({
      next: (response) => {
        console.log(response);
        this.userService.loginUser(this.temp).subscribe( {
          next :  (response) => {
            this.responseData = response;
            localStorage.setItem("jwt", this.responseData.token);
            this._snackBar.open("Account Created Successfully", "Okay");
            localStorage.setItem("isLoggedIn", 'true');
            this.userService.isLoggedIn.next(true);
            this.userService.authToken.next(this.responseData.token);
            this.router.navigateByUrl('');
          },
          error : (err) => {
            console.log(err);
          }
        }

        )
      },
      error : (err) => {
        let error;
        if(err.status == 409 ) {
          error = "User Already Exists! Please Login"
        }else {
          error = "Could not Register! Please Try After Some Time"
        }
        this._snackBar.open(error,"Close", {
          duration : 3000
        })
      }
    }
    )

  }

  get userName() { return this.registrationForm.get("userName") };
  get phoneNumber() { return this.registrationForm.get("phoneNumber") };
  get email() { return this.registrationForm.get("email") };
  get password() { return this.registrationForm.get("password") };
  get confirmPassword() { return this.registrationForm.get("confirmPassword") };

  mustMatchValidator(fg: AbstractControl) {
    const passwordValue = fg.get("password")?.value;
    const confirmPasswordValue = fg.get("confirmPassword")?.value;
    if (!passwordValue || !confirmPasswordValue) {
      return null;
    }
    if (passwordValue != confirmPasswordValue) {
      console.log("Inside");
      fg.get('confirmPassword')?.setErrors({ mustMatchError: true });
      return { mustMatchError: true }
    }
    return null;
  }

  check() {
    console.log(this.registrationForm.errors);
  }


  async canDeactivate() {
    if (this.registrationForm.dirty && this.registrationForm.invalid) {
      let response = await this.openDialog();
      console.log(response);
      return response;
    }
    else
      return true;
  }
  
  async openDialog(): Promise<any> {
    const dialogRef = this.dialog.open(ErrorDialogueComponent, {
      data: "Do you want to leave without registering?"
    });

    const source$ = dialogRef.afterClosed();
    const userResp = await lastValueFrom(source$);
    console.log("Using LastValue", userResp);

    return userResp ? true : false;
  }

}
