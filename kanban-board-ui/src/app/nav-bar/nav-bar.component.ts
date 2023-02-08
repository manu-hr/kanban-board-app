import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogCreateProjectComponent } from '../dialog-create-project/dialog-create-project.component';
import { UserService } from '../services/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {
  isLoggedIn:boolean = false;

  constructor(public dialog: MatDialog, private userService:UserService, private router: Router){}

  ngOnInit():void {
    this.userService.isLoggedIn.subscribe(data=> this.isLoggedIn = data)
  }

  openDialog() {
    this.dialog.open(DialogCreateProjectComponent);
  }

  logout():void {
    this.userService.logout();
    this.router.navigateByUrl('login');
  }
 
}
