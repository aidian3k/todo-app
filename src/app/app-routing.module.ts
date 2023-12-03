import {RouterModule, RouterOutlet, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {AuthGuard} from "./features/authentication/helpers/auth.guard";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'auth'
  },
  {
    path: 'auth',
    loadChildren: () => import('./features/authentication/authentication.module').then(m => m.AuthenticationModule)},
  {
    path: 'main-page',
    loadChildren: () => import('./features/main-page/main-page.module').then(m => m.MainPageModule),
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    loadComponent: () => import('./features/not-found/not-found.component').then(m => m.NotFoundComponent)
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterOutlet]
})
export class AppRoutingModule {}
