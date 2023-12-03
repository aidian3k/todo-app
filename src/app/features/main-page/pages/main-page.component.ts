import { Component, OnInit } from '@angular/core';
import {Store} from "@ngrx/store";
import {User} from "../../authentication/models/user.model";
import {
  selectCurrentUser
} from "../../../redux/current-user/reducer/current-user.reducer";
import {selectTasks} from "../../../redux/tasks/reducers/tasks.reducer";
import {TaskModel} from "../models/task.model";
import {
  FormBuilder,
  FormGroup, FormsModule,
  ReactiveFormsModule,
  Validators
} from "@angular/forms";
import {createNewTask} from "../../../redux/tasks/actions/task.actions";
import {NgForOf} from "@angular/common";
import {MainPageModule} from "../main-page.module";
import {
  logoutCurrentUser
} from "../../../redux/current-user/actions/current-user.actions";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  imports: [
    ReactiveFormsModule,
    NgForOf,
    MainPageModule,
    FormsModule
  ],
  standalone: true
})
export class MainPageComponent implements OnInit {
  currentUser: User | null;
  currentUserTasks: TaskModel[];
  addNewTaskFormGroup: FormGroup;
  constructor(private store: Store, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.initializeAddNewTaskFormGroup();
    debugger
    this.store.select(selectCurrentUser).subscribe(value => {
      this.currentUser = value;
    })
    this.store.select(selectTasks).subscribe(value => {
      this.currentUserTasks = value.filter(task => task.userId === this.currentUser?.id);
    })
  }

  addNewTask(): void {
    const newTask: TaskModel = {
      id: Math.floor(Math.random() * 100_000_000),
      userId: this.currentUser?.id,
      isDone: false,
      ...this.addNewTaskFormGroup.value,
      addedDate: new Date()
    }
    this.store.dispatch(createNewTask({newTask: newTask}))
    this.addNewTaskFormGroup.get('taskDescription')?.reset();
  }

  private initializeAddNewTaskFormGroup(): void {
    this.addNewTaskFormGroup = this.formBuilder.group({
      taskDescription: [null, [Validators.required, Validators.maxLength(255)]]
    })
  }

  logoutUser() {
    this.store.dispatch(logoutCurrentUser())
    this.router.navigateByUrl('/auth/login')
  }

  get sortedTaskModel(): TaskModel[] {
    return this.currentUserTasks.sort((a, b) => {
      return (b.addedDate.getTime() - a.addedDate.getTime())
    })
  }
}
