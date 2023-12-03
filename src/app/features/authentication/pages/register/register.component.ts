import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Store} from "@ngrx/store";
import {User} from "../../models/user.model";
import {registerUser} from "../../../../redux/users/actions/users.actions";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {
  registerFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private store: Store, private router: Router) { }

  ngOnInit(): void {
    this.initializeFormGroup();
  }

  registerUser(): void {
    const newUser: User = {...this.registerFormGroup.value, userName: this.registerFormGroup.get('username')?.value};
    this.store.dispatch(registerUser({newUser: {...newUser, id:  Math.floor(Math.random() * 100_000_000)}}));
    this.router.navigateByUrl('/auth/login')
  }

  private initializeFormGroup(): void {
    this.registerFormGroup = this.formBuilder.group({
      email: [null, [Validators.email, Validators.required]],
      username: [null, [Validators.maxLength(255), Validators.required]],
      password: [null, [Validators.minLength(6), Validators.maxLength(255), Validators.required]],
      confirmationPassword: [null, [Validators.required]]
    })
  }

}
