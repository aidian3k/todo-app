import {
  createFeatureSelector,
  createReducer,
  createSelector,
  on
} from "@ngrx/store";
import {createNewTask, removeTask, updateTask} from "../actions/task.actions";
import {TaskModel} from "../../../features/main-page/models/task.model";

export type TasksState = {
  tasks: TaskModel[]
}

export const mockPosts: TaskModel[] = [
  {id: 1, userId: 1, isDone: false, addedDate: new Date(), taskDescription: 'Schedule a meeting with the marketing team to discuss the upcoming campaign.'},
  {id: 2, userId: 2, isDone: false, addedDate: new Date(), taskDescription: 'Start reading the first chapter of the new book for the book club.'},
  {id: 3, userId: 1, isDone: false, addedDate: new Date(), taskDescription: 'Buy groceries for the week, including fruits, vegetables, and snacks.'},
  {id: 4, userId: 2, isDone: false, addedDate: new Date(), taskDescription: 'Review and respond to emails in the morning.'},
  {id: 5, userId: 1, isDone: false, addedDate: new Date(), taskDescription: 'Attend the networking event at 6:00 PM.'},
  {id: 6, userId: 2, isDone: false, addedDate: new Date(), taskDescription: 'Call the IT department to resolve computer issues.'},
  {id: 7, userId: 1, isDone: false, addedDate: new Date(), taskDescription: 'Take a 30-minute break for a quick workout or stretch.'},
  {id: 8, userId: 2, isDone: false, addedDate: new Date(), taskDescription: 'Write a blog post draft on the latest industry trends.'},
  {id: 9, userId: 1, isDone: false, addedDate: new Date(), taskDescription: 'Set a reminder to wish a friend happy birthday.'}
]

const usersInitialState: TasksState = {
  tasks: mockPosts
}

export const selectTasksState = createFeatureSelector<TasksState>('tasks');
export const selectTasks = createSelector(
  selectTasksState,
  (state: TasksState) => state.tasks
);

export const tasksReducer = createReducer(
  usersInitialState,
  on(createNewTask, (state, {newTask}) => ({
    ...state,
    tasks: [...state.tasks, newTask]
  })),
  on(updateTask, (state, {updateTask}) => ({
    ...state,
    tasks: [...state.tasks.filter(task => task.id !== updateTask.id), updateTask]
  })), on(removeTask, (state, {taskId}) => ({
    ...state,
    tasks: [...state.tasks.filter(task => task.id !== taskId)]
  })),
)
