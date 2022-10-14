import { Component, OnInit } from '@angular/core';
import {Problem} from "../../../model/Problem";
import {ActivatedRoute} from "@angular/router";
import {ProblemService} from "../../../services/problem.service";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  problemId: string;
  problem!: Problem;

  constructor(private activatedRoute: ActivatedRoute, private problemService: ProblemService) {
    // @ts-ignore
    this.problemId = this.activatedRoute.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    this.problemService.getProblem(this.problemId).subscribe(value => this.problem = value)
  }


}
