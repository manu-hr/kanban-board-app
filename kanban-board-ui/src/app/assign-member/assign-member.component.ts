import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectService } from '../services/project/project.service';

@Component({
  selector: 'app-assign-member',
  templateUrl: './assign-member.component.html',
  styleUrls: ['./assign-member.component.css']
})
export class AssignMemberComponent {
  constructor(private fb:FormBuilder, private _snackBar:MatSnackBar,private projectService:ProjectService,@Inject(MAT_DIALOG_DATA) public projectId: string){}
  memberForm = this.fb.group({
    email:['',[ Validators.required, Validators.pattern(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i)]]
  })
  get email() { return this.memberForm.get("email")};
  emailId:any;
  // onSubmit(){
  //   this.emailId=this.email?.value;
  //   this.projectService.addMember(this.emailId,this.projectId).subscribe({
  //     next : (data) => {
  //       this._snackBar.open("User Add as Member to this Project","success",{
  //         duration : 5000,
  //         panelClass: ['mat-toolbar', 'mat-primary']
  //       });
  //     },
  //     error : err => {
  //       this._snackBar.open('User Not registered In this app', 'Okay', {​
  //         duration: 5000,​
  //          panelClass: ['mat-toolbar', 'mat-danger']​
  //        }) 
   
  //     }

  //   })

  // }

}
