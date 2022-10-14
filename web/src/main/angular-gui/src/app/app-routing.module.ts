import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PlayComponent} from "./component/play/play.component";
import {NewComponent} from "./component/new/new.component";
import {GameComponent} from "./component/game/game/game.component";

const routes: Routes = [
  {path: 'play', component: PlayComponent},
  {path: 'play/:id', component: GameComponent},
  {path: 'new', component: NewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
