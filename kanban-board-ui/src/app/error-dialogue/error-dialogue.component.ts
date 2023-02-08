import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-error-dialogue',
  templateUrl: './error-dialogue.component.html',
  styleUrls: ['./error-dialogue.component.css']
})
export class ErrorDialogueComponent {

  constructor(public dialogRef: MatDialogRef<ErrorDialogueComponent>, @Inject(MAT_DIALOG_DATA) public errorMsg: string,) {}
}
