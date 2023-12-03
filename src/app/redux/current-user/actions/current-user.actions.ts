import {createAction, props} from "@ngrx/store";
import {User} from "../../../features/authentication/models/user.model";

export const loginCurrentUser = createAction('[Login Component] login', props<{loginUser: User | null}>());
export const logoutCurrentUser = createAction('[Login Component] logout');
