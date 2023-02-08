import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Project } from '../models/project';
import { ProjectService } from '../services/project/project.service';

@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.css']
})
export class EditProjectComponent {
  minDate: Date = new Date();
  
  constructor(private fb:FormBuilder,private _snackBar: MatSnackBar,private projectService:ProjectService,@Inject(MAT_DIALOG_DATA) public details:Project) {
    this.minDate.setDate(this.minDate.getDate());
   }

  editProjectForm=this.fb.group({
    projectId : [''],
    projectName : ['',Validators.required],
    projectDescription : ['',Validators.required],
    deadline :  ['']
  })
  get projectId(){return this.editProjectForm.get("projectId")}
  get projectName() { return this.editProjectForm.get("projectName")};
  get projectDescription() { return this.editProjectForm.get("projectDescription")};
  get deadline() { return this.editProjectForm.get("deadline")};

  ngOnInit() {
    console.log(this.details);
    this.projectId?.setValue(this.details?.projectId ?? '');
    this.projectName?.setValue(this.details?.projectName ?? '');
    this.projectDescription?.setValue(this.details?.projectDescription ?? '');
    this.deadline?.setValue(this.details?.deadline ?? '');
  }
 

}
