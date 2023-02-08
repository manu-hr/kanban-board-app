import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeViewComponent } from './home-view/home-view.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ProfileComponent } from './profile/profile.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { TaskFormComponent } from './task-form/task-form.component';
import { ProjectDashboardComponent } from './project-dashboard/project-dashboard.component';
import { AuthGuard } from './services/guard/auth/auth.guard';
import { ProjectHomeComponent } from './project-home/project-home.component';
import { CanDeactivateGuard } from './services/guard/canDeactivate/can-deactivate.guard';

const routes: Routes = [
  {
    path:'',
    canActivate:[AuthGuard],
    component: HomeViewComponent
  },
  {
    path:'login',
    component:LoginFormComponent
  },
  {
    path : 'profile',
    component:ProfileComponent
  },
  {
    path:'edit-profile',
    component: ProfileComponent
  },
  {
    path:'registration',
    canDeactivate: [CanDeactivateGuard],
    component: RegistrationFormComponent
  },
  {
    path : 'task-form',
    canActivate:[AuthGuard],
    component : TaskFormComponent
  },
  {
    path : 'project-dashboard/:id',
    canActivate:[AuthGuard],
    component : ProjectHomeComponent
  },
  {
    path : '**',
    redirectTo : ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }
