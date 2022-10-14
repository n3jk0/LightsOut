import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Solution} from "../model/Solution";

@Injectable({
  providedIn: 'root'
})
export class SolutionService {

  constructor(private http: HttpClient) { }

  getSolution(problemId: string): Observable<Solution> {
    // Better solution would be to add base url to proxy.conf.json file
    return this.http.get<Solution>('http://localhost:8080/api/solutions/problem/' + problemId)
  }
}
