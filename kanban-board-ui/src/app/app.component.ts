import { Component } from '@angular/core';
import { UserService } from './services/user/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn:boolean = false;
  title = 'kanban-board-ui';
  constructor(private userService:UserService){}
  ngOnInit():void {
    this.userService.isLoggedIn.subscribe(data=> this.isLoggedIn = data)
  }
}
