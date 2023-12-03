import {
  createFeatureSelector,
  createReducer,
  createSelector,
  on
} from "@ngrx/store";
import {User} from "../../../features/authentication/models/user.model";
import {
  loginCurrentUser,
  logoutCurrentUser
} from "../actions/current-user.actions";
import {UsersState} from "../../users/reducers/users.reducer";

export type CurrentUserState = {
  currentUser: User | null
}
const initialUserState: CurrentUserState = {
  currentUser: null
}

export const selectCurrentUserState = createFeatureSelector<CurrentUserState>('currentUser');
export const selectCurrentUser = createSelector(
  selectCurrentUserState,
  (state: CurrentUserState) => state.currentUser
);

export const currentUserReducer = createReducer(
  initialUserState,
  on(loginCurrentUser, (state, { loginUser }) => ({
    currentUser: loginUser
  })),
  on(logoutCurrentUser, (state) => ({
    currentUser: null
  }))
)
