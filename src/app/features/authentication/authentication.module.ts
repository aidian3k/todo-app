import {NgModule} from "@angular/core";
import {AuthenticationRoutingModule} from "./authentication-routing.module";
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {RouterLinkWithHref} from "@angular/router";
import {MatIconModule} from "@angular/material/icon";
import {NgIf} from "@angular/common";

@NgModule({
  imports: [AuthenticationRoutingModule, FormsModule, MatInputModule, MatButtonModule, RouterLinkWithHref, MatIconModule, ReactiveFormsModule, NgIf],
  declarations: [
    LoginComponent,
    RegisterComponent
  ]
})
export class AuthenticationModule {}
