import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';
import {AppRoutingModule} from "./app-routing.module";
import {
  currentUserReducer
} from "./redux/current-user/reducer/current-user.reducer";
import {usersReducers} from "./redux/users/reducers/users.reducer";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {tasksReducer} from "./redux/tasks/reducers/tasks.reducer";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    StoreModule.forRoot({
      currentUser: currentUserReducer,
      users: usersReducers,
      tasks: tasksReducer
    }, {}),
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
