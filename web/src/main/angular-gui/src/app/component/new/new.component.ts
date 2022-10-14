import {Component, OnInit, Output} from '@angular/core';
import {ProblemService} from "../../services/problem.service";
import {Problem} from "../../model/Problem";

@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.scss']
})
export class NewComponent implements OnInit {
  selectedSize!: number;
  state: string[] = [];
  savedProblem!: Problem;

  constructor(private problemService: ProblemService) { }

  ngOnInit(): void {

  }

  onChange() {
    this.reset();
  }

  reset() {
    this.state = [];
    for (let i = 0; i < this.selectedSize * this.selectedSize; i++) {
      this.state.push("0")
    }
  }

  selected(sel: number) {
    this.state[sel] = this.state[sel] === "1" ? "0" : "1";
  }

  onClickSubmit() {
    let problem: Problem = {
      id: -1,
      initialState : this.state.join(","),
      problemSize: this.selectedSize
    };
    this.problemService.postProblem(problem).subscribe(value => this.savedProblem = value,)
  }
}
