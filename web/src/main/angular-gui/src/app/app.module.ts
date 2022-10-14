import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { FlexLayoutModule } from "@angular/flex-layout";
import { PlayComponent } from './component/play/play.component';
import { NewComponent } from './component/new/new.component';
import { HttpClientModule } from "@angular/common/http";
import { ProblemComponent } from './component/game/problem/problem.component';
import { SquareComponent } from './component/game/square/square.component';
import { GameComponent } from './component/game/game/game.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PlayComponent,
    NewComponent,
    ProblemComponent,
    SquareComponent,
    GameComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    FlexLayoutModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
