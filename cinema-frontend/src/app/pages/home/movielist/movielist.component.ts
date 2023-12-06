import {Component, inject, OnInit} from '@angular/core';
import {ScreeningService} from "@services/home/screening.service";
import {Movie} from "@data/home/movie";
import {MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {map} from "rxjs";
import {MatNativeDateModule} from "@angular/material/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-movielist',
  templateUrl: './movielist.component.html',
  styleUrls: ['./movielist.component.scss'],
  standalone: true,
  imports: [MatTableModule, MatCardModule, MatDatepickerModule, MatNativeDateModule],
})
export class MovielistComponent implements OnInit {

  screeningService = inject(ScreeningService);

  selected : Date = new Date();
  
  constructor(private router: Router) { }

  movies: Movie[] = [];
  displayedColumns: string[] = ['id', 'title', 'duration', 'minimalAge'];


  ngOnInit(): void {
    console.log("Current date: " + this.selected);
    this.screeningService.getScreeningsForGivenDate(this.selected).pipe(
        map(screenings => screenings.map(screening => screening.movie))
    ).subscribe(movies => this.movies = movies);
  }

  dateChanged() {
    this.selected.setHours(10, 0);
    console.log("Date changed: " + this.selected);
    this.screeningService.getScreeningsForGivenDate(this.selected).pipe(
        map(screenings => screenings.map(screening => screening.movie))
    ).subscribe(movies => this.movies = movies);
  }

  showMovieDetails(movieId: number) {
    this.router.navigate(['/home/movie_details'], { queryParams: { id: movieId } });
  }
}
