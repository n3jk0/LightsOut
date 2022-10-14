import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-square',
  templateUrl: './square.component.html',
  styleUrls: ['./square.component.scss']
})
export class SquareComponent implements OnInit {
  @Input() on!: boolean;
  @Input() clickable!: boolean;
  @Input() small!: boolean;
  @Input() inSolution!: boolean;
  @Input() squareIdent!: number
  @Output() clickEvent = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {

  }

  makeMove() {
    this.clickEvent.emit(this.squareIdent);
  }
}
