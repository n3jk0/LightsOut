import {Component, Input, OnInit, Output} from '@angular/core';
import {Problem} from "../../../model/Problem";
import {Solution} from "../../../model/Solution";
import {SolutionService} from "../../../services/solution.service";

@Component({
  selector: 'app-problem',
  templateUrl: './problem.component.html',
  styleUrls: ['./problem.component.scss']
})
export class ProblemComponent implements OnInit {
  @Input() problem!: Problem;
  @Input() small!: boolean;
  @Input() clickable!: boolean;
  @Output() state!: string[];

  solution!: Solution;
  solutionSteps: number[] = [];

  constructor(private solutionService: SolutionService) { }

  ngOnInit(): void {
    this.state = this.problem.initialState.split(',');
    this.solutionService.getSolution(this.problem.id.toString()).subscribe(value => this.solution = value)
  }

  makeMove(clickedSquare: number) {
    const problemSize = this.problem.problemSize
    for (let i = 0; i < this.state.length; i++) {
      // 1 if on; 0 if off
      let currentLight: string = this.state[i]
      if (i == clickedSquare) {
        // Toggle clicked
        this.state[i] = this.triggerLight(currentLight)
      } else if (clickedSquare % problemSize != 0 && i === clickedSquare - 1) {
        // Toggle left
        this.state[i] = this.triggerLight(currentLight)
      } else if (clickedSquare % problemSize != (problemSize - 1) && i === clickedSquare + 1) {
        // Toggle right
        this.state[i] = this.triggerLight(currentLight)
      } else if ((clickedSquare - problemSize) >= 0 && i === clickedSquare - problemSize) {
        // Toggle up
        this.state[i] = this.triggerLight(currentLight)
      } else if ((clickedSquare + problemSize) < (problemSize * problemSize) && i === clickedSquare + problemSize) {
        // Toggle down
        this.state[i] = this.triggerLight(currentLight)
      }
    }

    if (this.isFinished()) {
      alert("Congratulations!")
    }
  }

  private triggerLight(light: string) {
    return light === '1' ? '0' : '1';
  }

  reset() {
    this.state = this.problem.initialState.split(",")
  }

  showSolution() {
    this.reset()
    this.solutionSteps = [];
    this.solution.solutionSteps.forEach(value => this.solutionSteps.push(value.move))
  }

  isFinished() {
    return  this.state.every(value => value === "0")
  }
}
