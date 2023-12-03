import {createAction, props} from "@ngrx/store";
import {User} from "../../../features/authentication/models/user.model";

export const registerUser = createAction('[Register Component] registerUser', props<{newUser: User}>());
