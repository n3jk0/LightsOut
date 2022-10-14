import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Problem} from "../model/Problem";

@Injectable({
  providedIn: 'root'
})
export class ProblemService {

  constructor(private http: HttpClient) { }

  getProblems(): Observable<Problem[]> {
    // Better solution would be to add base url to proxy.conf.json file
    return this.http.get<Problem[]>('http://localhost:8080/api/problems')
  }

  getProblem(id: string): Observable<Problem> {
    // Better solution would be to add base url to proxy.conf.json file
    return this.http.get<Problem>('http://localhost:8080/api/problems/' + id)
  }

  postProblem(problem: Problem): Observable<Problem> {
    return this.http.post<Problem>('http://localhost:8080/api/problems/', problem)
  }
}
