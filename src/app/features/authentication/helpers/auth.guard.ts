import {Injectable} from "@angular/core";
import {
  ActivatedRouteSnapshot,
  CanActivate, Router,
  RouterStateSnapshot, UrlTree
} from "@angular/router";
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {
  CurrentUserState, selectCurrentUser
} from "../../../redux/current-user/reducer/current-user.reducer";
import {User} from "../models/user.model";

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
  currentUser: User | null;
  constructor(private store: Store, private router: Router) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    this.store.select(selectCurrentUser).subscribe(value => {
      this.currentUser = value;
    })
    if(!this.currentUser) {
      return this.router.navigateByUrl('/auth/login')
    }

    return true;
  }

}
