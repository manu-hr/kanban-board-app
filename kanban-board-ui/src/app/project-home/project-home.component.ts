import { Component } from '@angular/core';
import { ProjectService } from '../services/project/project.service';
import { Project } from '../models/project';
import { ActivatedRoute } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserService } from '../services/user/user.service';

@Component({
  selector: 'app-project-home',
  templateUrl: './project-home.component.html',
  styleUrls: ['./project-home.component.css']
})
export class ProjectHomeComponent {

  projects: Project[] = [];
  projectId = new BehaviorSubject<string>('')
  openSidenav:boolean = true;
  private screenWidth$ = new BehaviorSubject<number>(window.innerWidth);

  constructor(private projectService: ProjectService, private activatedRoute: ActivatedRoute, private userService:UserService) { }

  ngOnInit(): void {
    this.userService.projects.subscribe(data => this.projects = data);
    this.activatedRoute.paramMap.subscribe(res => {
      this.projectId.next(res.get('id') ?? '');
    });

    this.getScreenWidth().subscribe(width => {
      if (width < 640) {
       this.openSidenav = false;
     }
     else if (width > 640) {
       this.openSidenav = true;
     }
   });

    this.getProjectByUser();
  }

  getProjectByUser(): void {
    this.projectService.getProjectsByUser().subscribe(
      response => {
        console.log(response);
        this.userService.projects.next(response);
      }
    )
  }

  getScreenWidth(): Observable<number> {
    return this.screenWidth$.asObservable();
  }

  setProjectId(id:string) {
    console.log(id);
    
    this.projectId.next(id);
  }
}
