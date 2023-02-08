import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ObjectID } from 'bson';
import { TaskFormComponent } from '../task-form/task-form.component';
import { Task } from '../models/task';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent {

  statusList:[] = [];
  task:Task | undefined;
  minDate: Date = new Date();

  taskForm = this.fb.group({
    taskId : [new ObjectID().toString()],
    taskName: ['', Validators.required],
    taskDescription: ['', Validators.required],
    priority: ['', Validators.required],
    assignedDateAndTime: new FormControl((new Date()).toISOString().substring(0, 10)),
    deadlineDateAndTime: ['', Validators.required],
    status: ['', Validators.required],
    assignedTo : [['']],
    assignee : ['']
  })

  constructor(private fb: FormBuilder, 
    private _snackBar: MatSnackBar, 
    public dialogRef: MatDialogRef<EditTaskComponent>, @Inject(MAT_DIALOG_DATA) public data:any ) { }

  get taskId() { return this.taskForm.get("taskId")}
  get taskName() { return this.taskForm.get("taskName")}
  get taskDescription() { return this.taskForm.get("taskDescription")}
  get priority() { return this.taskForm.get("priority")}
  get assignedDateAndTime() { return this.taskForm.get("assignedDateAndTime")}
  get deadlineDateAndTime() { return this.taskForm.get("deadlineDateAndTime")}
  get assignedTo() { return this.taskForm.get("assignedTo")}
  get assignee() { return this.taskForm.get("assignee")}
  get status() { return this.taskForm.get("status")}


  ngOnInit() {
    console.log(this.data);
    
    this.statusList = this.data.statusList;
    this.task = this.data.task;

    this.taskId?.setValue(this.task?.taskId ?? '');
    this.taskName?.setValue(this.task?.taskName ?? '');
    this.taskDescription?.setValue(this.task?.taskDescription ?? '');
    this.priority?.setValue(this.task?.priority ?? '');
    this.deadlineDateAndTime?.setValue(this.task?.deadlineDateAndTime ?? '');
    this.assignedTo?.setValue(this.task?.assignedTo ?? []);
    this.assignee?.setValue(this.task?.assignee ??'')
    this.status?.setValue(this.task?.status ?? '');

  }

}
