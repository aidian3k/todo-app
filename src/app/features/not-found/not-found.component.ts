import { Component, OnInit } from '@angular/core';
import {RouterLinkWithHref, RouterModule, RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  imports: [RouterOutlet, RouterLinkWithHref],
  standalone: true
})
export class NotFoundComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
