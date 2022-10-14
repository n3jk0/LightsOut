import { Component, OnInit } from '@angular/core';
import { Problem } from "../../model/Problem";
import { ProblemService } from "../../services/problem.service";

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.scss']
})
export class PlayComponent implements OnInit {
  problems: Problem[] = [];

  constructor(private problemService: ProblemService) { }

  ngOnInit(): void {
    this.problemService.getProblems().subscribe((problems) => this.problems = problems)
  }

}
