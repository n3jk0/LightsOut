import {SolutionStep} from "./SolutionStep";

export interface Solution {
  id: number;
  solutionSteps: SolutionStep[];
}
