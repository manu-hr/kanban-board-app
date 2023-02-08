import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Task } from '../models/task';
import { ObjectID } from 'bson';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent {

  minDate: Date = new Date();

  taskForm = this.fb.group({
    taskId : [new ObjectID().toString()],
    taskName: ['', Validators.required],
    taskDescription: ['', Validators.required],
    priority: ['', Validators.required],
    assignedDateAndTime: new FormControl((new Date()).toISOString().substring(0, 10)),
    deadlineDateAndTime: ['', Validators.required],
    status: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private _snackBar: MatSnackBar, public dialogRef: MatDialogRef<TaskFormComponent>, @Inject(MAT_DIALOG_DATA) public statusList: String[]) { 
    this.minDate.setDate(this.minDate.getDate());
  }


}
