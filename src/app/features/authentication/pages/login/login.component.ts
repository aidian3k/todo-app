import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/user.model";
import {LoginRequest} from "../../models/login-request.model";
import {Store} from "@ngrx/store";
import {selectUsers} from "../../../../redux/users/reducers/users.reducer";
import {
  loginCurrentUser
} from "../../../../redux/current-user/actions/current-user.actions";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  loginFormGroup: FormGroup;
  loginError = false;
  users: User[];

  constructor(private router: Router, private store: Store, private fb: FormBuilder) { }

  ngOnInit(): void {
   this.initializeLoginFormGroup();
   this.store.select(selectUsers).subscribe({
     next: value => {
       this.users = value;
     }
   })
  }

  private initializeLoginFormGroup(): void {
    this.loginFormGroup = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    })
  }

  loginUser() {
    const loginRequest: LoginRequest = this.loginFormGroup.value;
    const foundUser =  this.users.find(user => user.email === loginRequest.email);

    if(!foundUser || foundUser.password !== loginRequest.password) {
      this.loginError = true;
      return;
    }

    this.store.dispatch(loginCurrentUser({loginUser: foundUser}))
    this.router.navigateByUrl('/main-page')
  }
}
