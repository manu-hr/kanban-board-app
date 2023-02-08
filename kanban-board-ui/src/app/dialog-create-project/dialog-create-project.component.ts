import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProjectService } from '../services/project/project.service';

@Component({
  selector: 'app-dialog-create-project',
  templateUrl: './dialog-create-project.component.html',
  styleUrls: ['./dialog-create-project.component.css']
})
export class DialogCreateProjectComponent {
  minDate: Date = new Date();

  constructor(private fb:FormBuilder,private _snackBar: MatSnackBar,private projectService:ProjectService) { 
    this.minDate.setDate(this.minDate.getDate());
   }

  projectFormData=this.fb.group({
    projectName : ['',Validators.required],
    projectDescription : ['',Validators.required],
    deadline :  ['']
  })
  get projectName() { return this.projectFormData.get("projectName")};
  get projectDescription() { return this.projectFormData.get("projectDescription")};
  get deadline() { return this.projectFormData.get("deadline")};

  projectData()
  {
    console.log(this.projectFormData.value);
    this.addProject();
  }
  
   
  addProject()
  {

    let tempData:any = this.projectFormData.value;
    
    this.projectService.createProject(tempData).subscribe(
      response=>
      {
        
        
        console.log(response);
        this._snackBar.open("Project Added Successfully", "Done");
      }
    )
  }

}
