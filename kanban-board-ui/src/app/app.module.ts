import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ProfileComponent } from './profile/profile.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { DialogProfileElementBoxComponent } from './dialog-profile-element-box/dialog-profile-element-box.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { HomeViewComponent } from './home-view/home-view.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ColumnLimitDialog, ProjectDashboardComponent } from './project-dashboard/project-dashboard.component';

import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import { TaskCardComponent } from './task-card/task-card.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {MatToolbarModule} from '@angular/material/toolbar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDividerModule} from '@angular/material/divider';
import { TaskFormComponent } from './task-form/task-form.component';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatMenuModule} from '@angular/material/menu';
import { DialogCreateProjectComponent } from './dialog-create-project/dialog-create-project.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
import { AssignMemberComponent } from './assign-member/assign-member.component';
import {MatChipsModule} from '@angular/material/chips';
import { EditProjectComponent } from './edit-project/edit-project.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import { ProjectHomeComponent } from './project-home/project-home.component';
import {MatListModule} from '@angular/material/list';
import { FooterComponent } from './footer/footer.component';
import { ErrorDialogueComponent } from './error-dialogue/error-dialogue.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegistrationFormComponent,
    NavBarComponent,
    ProfileComponent,
    HomeViewComponent,
    LoginFormComponent,
    DialogProfileElementBoxComponent,
    ProjectDashboardComponent,
    TaskCardComponent,
    TaskFormComponent,
    DialogCreateProjectComponent,
    EditTaskComponent,
    AssignMemberComponent,
    EditProjectComponent,
    ProjectHomeComponent,
    FooterComponent,
    ColumnLimitDialog,
    ErrorDialogueComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatExpansionModule,
    MatDialogModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatToolbarModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatSnackBarModule,
    MatDividerModule,
    HttpClientModule,
    MatCardModule,
    DragDropModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatMenuModule,
    MatChipsModule,
    MatSidenavModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
