import { LOCATION_INITIALIZED } from '@angular/common';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogProfileElementBoxComponent } from '../dialog-profile-element-box/dialog-profile-element-box.component';
import { User } from '../models/user';
import { EditProfileService } from '../services/dashBoard/edit-profile.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  userDetails:any='';
  profilePicForm = this.fb.group({
    profile: ['']
  })

  constructor(
    public dialog: MatDialog,
    private userService:EditProfileService,
    private fb:FormBuilder
  ) {}

  get profile(){ return this.profilePicForm.get('profile')}

  ngOnInit():void {
    this.viewProfile();   
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogProfileElementBoxComponent, {
      data : this.userDetails
    });

    dialogRef.afterClosed().subscribe((result:User) => {
      console.log('The dialog was closed', result);
      if(result != null || result != undefined) { 
        this.userService.updateProfileDetails(result).subscribe({
          next: (data) => {
            console.log(data);
            this.userDetails = data;
          }
        })
      }

    });
  }

  viewProfile()
  {
    this.userService.viewProfile().subscribe(
      response=>
      {
        console.log(response);
        this.userDetails=response;
        console.log(response);  
      }
    )
  }

  uploadPic(event:any):void {
    const formData = new FormData();
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.profile?.setValue(file);
    }
    
    formData.append('image', this.profilePicForm.get('profile')?.value ?? '');
    this.userService.uploadProfilePic(formData).subscribe({
      next: data => {
        console.log(data);
        this.userDetails = data;
      }
    })

  }

}
