import {createAction, props} from "@ngrx/store";
import {TaskModel} from "../../../features/main-page/models/task.model";

export const createNewTask = createAction('[Main Page Component] createTask', props<{newTask: TaskModel}>());
export const updateTask = createAction('[Task Component] updateTask', props<{updateTask: TaskModel}>())
export const removeTask = createAction('[Task Component] removeTask', props<{taskId: number}>())
