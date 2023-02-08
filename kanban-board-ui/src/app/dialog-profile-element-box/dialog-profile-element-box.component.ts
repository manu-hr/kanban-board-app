import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../models/user';
import { EditProfileService } from '../services/dashBoard/edit-profile.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-profile-element-box',
  templateUrl: './dialog-profile-element-box.component.html',
  styleUrls: ['./dialog-profile-element-box.component.css']
})
export class DialogProfileElementBoxComponent {

  profileEditForm = this.fb.group({
    userId : [''],
    userName: ['', Validators.required],
    email: ['', [Validators.required, Validators.pattern(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i)]],
    phoneNumber: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
    password: [''],
  })

  constructor(private fb: FormBuilder, 
    private _snackBar: MatSnackBar, 
    private userService: EditProfileService,
    @Inject(MAT_DIALOG_DATA) public data:any 
  ) { }

  ngOnInit():void {
    this.userId?.setValue(this.data.userId);
    this.userName?.setValue(this.data.userName);
    this.email?.setValue(this.data.email);
    this.phoneNumber?.setValue(this.data.phoneNumber);
    this.password?.setValue(this.data.password);
  }

  get userId() { return this.profileEditForm.get("userId") };
  get userName() { return this.profileEditForm.get("userName") };
  get phoneNumber() { return this.profileEditForm.get("phoneNumber") };
  get email() { return this.profileEditForm.get("email") };
  get password() { return this.profileEditForm.get("password") };

  userDetails: any = this.profileEditForm.value;
}
