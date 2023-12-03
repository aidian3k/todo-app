import {NgModule} from "@angular/core";
import { TaskComponent } from './components/task/task.component';
import {MainPageRoutingModule} from "./main-page-routing.module";

@NgModule({
    imports: [MainPageRoutingModule],
    exports: [
        TaskComponent
    ],
    declarations: [
        TaskComponent
    ]
})
export class MainPageModule {}
