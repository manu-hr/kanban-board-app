
import { Component, Inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AssignMemberComponent } from '../assign-member/assign-member.component';
import { DialogCreateProjectComponent } from '../dialog-create-project/dialog-create-project.component';
import { EditProjectComponent } from '../edit-project/edit-project.component';
import { EditTaskComponent } from '../edit-task/edit-task.component';
import { Project } from '../models/project';
import { EditProfileService } from '../services/dashBoard/edit-profile.service';
import { ProjectService } from '../services/project/project.service';
import { UserService } from '../services/user/user.service';
import { ErrorDialogueComponent } from '../error-dialogue/error-dialogue.component';

@Component({
  selector: 'app-home-view',
  templateUrl: './home-view.component.html',
  styleUrls: ['./home-view.component.css']
})
export class HomeViewComponent {

  projectDetails: Project[] = [];
  errorText : string = '';

  constructor(private projectService: ProjectService, public dialog: MatDialog, private _snackBar: MatSnackBar, private userService:UserService) {  }

  ngOnInit():void {
    this.getProjectByUser();  
    this.userService.projects.subscribe(data => this.projectDetails = data);
  }

  getProjectByUser() {
    this.projectService.getProjectsByUser().subscribe({
      next: (response) => {
        console.log(response);
        this.userService.projects.next(response);
        this.projectDetails = response;
      },
      error : (err) => {
        console.log(err.status);
      }
    }

    )
  }

  openDialogAddMembers(id: string) {
    const dialogRef = this.dialog.open(AssignMemberComponent, {
      data: id
    });
    dialogRef.afterClosed().subscribe((res) => {
      console.log('The dialog was closed');
      this.projectService.addMember(res.email, id).subscribe({
        next: (data) => {
          console.log(data);
          this.getProjectByUser();
          this._snackBar.open("User Add as Member to this Project", "success", {
            duration: 5000,
            panelClass: ['mat-toolbar', 'mat-primary']
          });
        },
        error: err => {
          const dialogRef = this.dialog.open(ErrorDialogueComponent, {
            data: "User Not registered In this app"
          });
        }
      })
    })
  }

  openDialogEditProject(details: Project) {
    const dialogRef = this.dialog.open(EditProjectComponent, {
      data: details
    });
    dialogRef.afterClosed().subscribe((res) => {
      console.log('The dialog was closed');
      this.getProjectByUser();
      this.projectService.editProject(res).subscribe({
        next: (data:any) => {
          console.log(data);
          this.projectDetails.forEach((project:Project, index:number) => {
            if(project.projectId == data.projectId) {
              this.projectDetails[index] = data;
            }
          })
          this._snackBar.open("Project edited", "success", {
            duration: 3000,
            panelClass: ['mat-toolbar', 'mat-primary']
          });
        },
        error : (err) => {
          this._snackBar.open("Something went wrong! Please try again!", "OK", {
            duration:3000
          });
        }
      })
    })
  }

  deleteProject(projectId: string) {
    const dialogRef = this.dialog.open(ErrorDialogueComponent, {
      data: "Do You Want To Delete This Project?"
    });
    dialogRef.afterClosed().subscribe((res) => {
      if(!res)
       return;
      this.projectService.deleteProject(projectId).subscribe(
        (response : any) => {
          console.log(response);
          if(response !=null)
            this.projectDetails = response;
          this.projectDetails = [];          
          this.getProjectByUser();
          this._snackBar.open('Project Deleted', 'Okay', {
            duration: 3000,
            panelClass: ['mat-toolbar', 'mat-danger']
          })
        }
      )
    })


  }


  openDialog1() {
    const dialogRef = this.dialog.open(DialogCreateProjectComponent);

    dialogRef.afterClosed().subscribe((result:Project) => {
      console.log('The dialog was closed', result);
      if(result != null || result != undefined) { 
        this.projectService.createProject(result).subscribe({
          next :  (response:any)=>
          {
             this.projectDetails.push(response);
              console.log(response);
            this._snackBar.open("Project Added Successfully", "Done");
          },
          error : (err) => {
            if(err.status == 409) {
              this._snackBar.open("Please Select Different Project Name", "Ok",{
                duration:3000
              });
            }else {
              this._snackBar.open("Something went wrong! Please try Again!", "Ok",{
                duration:3000
              });
            }
          }
        }
         
        )
      }

    });
  }

}
