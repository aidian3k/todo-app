import {RouterModule, RouterOutlet, Routes} from "@angular/router";
import {MainPageComponent} from "./pages/main-page.component";
import {NgModule} from "@angular/core";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    loadComponent: () => import('./pages/main-page.component').then(m => m.MainPageComponent)
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterOutlet]
})
export class MainPageRoutingModule {}
