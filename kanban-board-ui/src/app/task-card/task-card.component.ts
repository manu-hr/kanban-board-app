import { Component, Input } from '@angular/core';
import { Task } from '../models/task';
import { UserService } from '../services/user/user.service';
import { User } from '../models/user';
import { ProjectService } from '../services/project/project.service';
import { Column } from '../models/column';
import { MatDialog } from '@angular/material/dialog';
import { EditTaskComponent } from '../edit-task/edit-task.component';
import { BehaviorSubject } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-task-card',
  templateUrl: './task-card.component.html',
  styleUrls: ['./task-card.component.css']
})
export class TaskCardComponent {

  @Input()
  task:Task | undefined;

  @Input()
  projectId:String | undefined;

  assignedTo = new BehaviorSubject<User[]>([]);

  priority_icon:string = 'text-emerald-500';

  members : User[] = [];
  columns : Column[] = [];

  constructor( private userService:UserService, 
      private projectService:ProjectService, 
      public dialog: MatDialog,
      private _snackBar: MatSnackBar
  ) {}

  ngOnInit():void {
    this.userService.members.subscribe(data => {
      this.members = data;
      this.getTaskAssignedUsers();
    });
    this.userService.columns.subscribe(data => this.columns = data);

    switch(this.task?.priority){
      case 'High' : this.priority_icon = 'text-red-500';
        break;
      case 'Medium' : this.priority_icon = 'text-amber-500';
        break;
      case 'Low' : this.priority_icon = 'text-emerald-500';
        break;
    }

    //  console.log(this.task);
    
  }

  deleteTask() : void {
    console.log(this.task);
    this.columns.map((item:Column) => {
       item.task = item.task.filter((taskItem:Task) => taskItem.taskId != this.task?.taskId)
    })
    console.log(this.columns);
    this.updateColumn();
  }


  updateColumn() {
    this.projectService.updateColumnWithTask(this.projectId ?? "",this.columns).subscribe({
      next: data =>{
        console.log(data.data.columns);
        this.userService.columns.next(data.data.columns ?? []);
      }
    });
  }

  assignMemberToTask(id:string) {
    this.columns.map((columnItem:Column) => {
      columnItem.task.map((taskItem:Task)=>{
          if(taskItem.taskId == this.task?.taskId) {
            let memberCurrentTaskCount = this.getMemberCurrentTaskCount(columnItem,id);    
            let taskLimit = columnItem.taskLimit ?? 0;
            if(taskItem.assignedTo.indexOf(id) != -1) {
              this._snackBar.open("Member Has Been Already Assigned!", "Close", {
                duration : 3000
              });
              return;
            }
            if(taskLimit  <= memberCurrentTaskCount) {
              this._snackBar.open("Member Has Reached Task Limit In This Column!", "Close", {
                duration : 3000
              });
              return;
            }    
            taskItem.assignedTo?.push(id);
            this.task?.assignedTo.push(id);
          }
      })
    })
    this.getTaskAssignedUsers();
    this.updateColumn();
  }

  removeMemberFromTask(id:string) {
    this.columns.map((columnItem:Column) => {
      columnItem.task.map((taskItem:Task)=>{
          if(taskItem.taskId == this.task?.taskId) {  
            let index = taskItem.assignedTo.indexOf(id);
            if(index != -1) {
              taskItem.assignedTo.splice(index,1);
              this.task?.assignedTo.splice(index,1);
              return;
            }
          }
      })
    })
    this.getTaskAssignedUsers();
    this.updateColumn();
  }

  getMemberCurrentTaskCount(column:Column, id:string) : number {
    let count = 0;
    column.task.forEach((item:Task) => {
      if(item.assignedTo.indexOf(id) != -1 ) {
        count++;
      }
    })
    return count;
  }


  getTaskAssignedUsers() {
    let tempAssignedTo = this.members.filter((el) => {
      return this.task?.assignedTo.some((f) => {
        return f === el.userId;
      });
    });
    this.assignedTo.next(tempAssignedTo);    
  }

  openDialog() {
    let statusList:String[] = [];
    statusList = this.columns!.map((item:Column) => item.columnTitle)
    const dialogRef =  this.dialog.open(EditTaskComponent, {
      data : { task : this.task, statusList:statusList } 
    });
    
    dialogRef.afterClosed().subscribe((result:Task) => {
      console.log('The dialog was closed', result);
      if(result != null || result != undefined) { 
        this.columns?.map((item:Column) => {
         item.task =  item.task.map((taskItem:Task) =>{
              return taskItem.taskId == result.taskId ? result : taskItem;
          })
        });
        console.log(this.columns);
        this.updateColumn();
      }
    });
  }

}
