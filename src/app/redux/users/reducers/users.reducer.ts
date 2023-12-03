import {
  createFeatureSelector,
  createReducer,
  createSelector,
  on
} from "@ngrx/store";
import {User} from "../../../features/authentication/models/user.model";
import {registerUser} from "../actions/users.actions";

export const mockUsers: User[] = [
  {id: 1, userName: 'Adrian', email: 'adrian@wp.pl', password: 'adrian'},
  {id: 2, userName: 'MWO-testing', email: 'mwo@wp.pl', password: 'mwo-test'}
]

export type UsersState = {
  users: User[]
}

const usersInitialState: UsersState = {
  users: mockUsers
}

export const selectUsersState = createFeatureSelector<UsersState>('users');
export const selectUsers = createSelector(
  selectUsersState,
  (state: UsersState) => state.users
);

export const usersReducers = createReducer(
  usersInitialState,
  on(registerUser, (state, {newUser}) => ({
    ...state,
    users: [...state.users, newUser]
  }))
)
