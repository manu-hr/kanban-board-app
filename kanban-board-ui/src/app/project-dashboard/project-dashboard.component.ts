import { Component, ElementRef, Inject, Input, QueryList, ViewChildren } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Column } from '../models/column';
import { Task } from '../models/task';
import { ProjectService } from '../services/project/project.service';
import { Project } from '../models/project';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { TaskFormComponent } from '../task-form/task-form.component';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user/user.service';
import { User } from 'src/app/models/user';
import { FormBuilder } from '@angular/forms';
import { ErrorDialogueComponent } from '../error-dialogue/error-dialogue.component';

@Component({
  selector: 'app-project-dashboard',
  templateUrl: './project-dashboard.component.html',
  styleUrls: ['./project-dashboard.component.css']
})
export class ProjectDashboardComponent {

  project:Project | undefined;
  columns: Column[] = [] ;
  showDropdown:string = "hidden";
  @Input()
  projectId:string ='';
  members : User[] = [];
  @ViewChildren('formRow',{read: ElementRef}) rows?:QueryList<ElementRef>;

  constructor(private projectService:ProjectService, public dialog: MatDialog, private userService:UserService, elementRef: ElementRef) {}

  ngOnInit(): void {
    this.userService.members.subscribe(data => this.members = data );
    this.userService.columns.subscribe(data => this.columns = data);
    this.getProjectDetails()
  }


  getProjectDetails():void {
    this.projectService.getProjectById(this.projectId).subscribe({
      next : data => {

        this.project = data;
        this.userService.columns.next(this.project?.columns ?? []);
        this.getProjectMembers(this.project?.members ?? []);
      },
      error : err =>{
        console.log(err);
      }
    })
  } 

  getProjectMembers(ids:string[]):void {
    this.userService.getAllUserByIds(ids).subscribe({
      next : data => {
        // console.log(data)
        this.userService.members.next(data)
      } 
    })
  }


  drop(event: CdkDragDrop<Task[]>, columnName:string) {
    console.log(columnName);
    let task = event.container.data;
    console.log(task.length);
    
    if (event.previousContainer === event.container) {
      // console.log(this.columns);
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
      // console.log("updated COlumns",this.columns);
      this.updateColumn();
    }
  }

  addColumn() {
    let newColumn:Column = {
      columnId: '',
      columnTitle: '',
      task: [],
      taskLimit: 3
    }
    this.columns?.push(newColumn);
    console.log(this.rows?.last.nativeElement.focus);
    
    this.projectService.addColumn(this.projectId,newColumn).subscribe({
      next: data =>{
        this.userService.columns.next(data.data.columns ?? []);
      }
    });
    console.log("Hi");
    setTimeout(()=> {
      this.rows?.last.nativeElement.focus()
    },500)
  }

  //update all columns 
  updateColumn() {
    // console.log("updated Columns",this.columns);
    this.projectService.updateColumnWithTask(this.projectId,this.columns).subscribe({
      next: data =>{
        // console.log(data.data.columns);
        this.userService.columns.next(data.data.columns ?? []);
      }
    });
  }

  //To Edit particular colum properties
  updateColumnDetails(column:Column) {
    this.projectService.updateColumnDetails(this.projectId,column).subscribe({
      next: data =>{
        console.log(data);
      }
    });
  }

  deleteColumn(id:any) {
    console.log("Hi");
    console.log(id);
    const dialogRef = this.dialog.open(ErrorDialogueComponent, {
      data: "Do You Want To Delete This Column?"
    });
    dialogRef.afterClosed().subscribe((res) => {
      if(!res)
       return;
      this.projectService.deleteColumn(this.projectId,id).subscribe({
        next : data =>{
          console.log(data);
          this.userService.columns.next(data.data.columns ?? []);
        }
      })

    })

  }

  openDialog() {
    let statusList:String[] = [];
    statusList = this.columns!.map((item:Column) => item.columnTitle)
    const dialogRef =  this.dialog.open(TaskFormComponent, {
      data : statusList
    });
    dialogRef.afterClosed().subscribe((result:Task) => {
      console.log('The dialog was closed', result);
      if(result != null || result != undefined) { 
        this.columns?.map((item:Column) => {
          if(item.columnTitle == result.status) {
            result.assignedTo = [];
            item.task.push(result)
          }
        });
        console.log(this.columns);
        this.updateColumn();
      }

    });
  }

  openColumnTaskLimitDialog(column:Column) {
    const dialogRef =  this.dialog.open(ColumnLimitDialog);
    dialogRef.afterClosed().subscribe((result:any) => {
      console.log('The dialog was closed', result);
      column.taskLimit = result.taskLimit;
      this.updateColumnDetails(column);      
    });
  }
  

}


@Component({
  selector: 'app-project-column-limit',
  templateUrl: 'project-column-limit.dialog.html',
})
export class ColumnLimitDialog {
  memberForm = this.fb.group({
    taskLimit:['']
  })
  constructor(public dialogRef: MatDialogRef<ColumnLimitDialog>, private fb:FormBuilder) {}
}
