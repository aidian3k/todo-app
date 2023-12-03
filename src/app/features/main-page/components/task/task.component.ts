import {Component, Input, OnInit} from '@angular/core';
import {TaskModel} from "../../models/task.model";
import {Store} from "@ngrx/store";
import {
  removeTask,
  updateTask
} from "../../../../redux/tasks/actions/task.actions";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html'
})
export class TaskComponent {
  @Input() taskModel: TaskModel;
  constructor(private store: Store) { }

  updateCurrentTask(): void {
    this.store.dispatch(updateTask({updateTask: {...this.taskModel, isDone: !this.taskModel.isDone}}))
  }

  removeTask(): void {
    this.store.dispatch(removeTask({taskId: this.taskModel.id}))
  }

  getTaskDescriptionClass(): string {
    return this.taskModel.isDone ? "w-full line-through text-green" : "w-full text-green";
  }
}
