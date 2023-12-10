import {AfterViewInit, Component, inject, OnInit, ViewChild} from '@angular/core';
import {ScreeningService} from "@services/home/screening.service";
import {Movie} from "@data/home/movie";
import {MatRow, MatTableDataSource, MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {NgIf} from "@angular/common";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {BehaviorSubject, map} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-movielist',
  templateUrl: './movielist.component.html',
  styleUrls: ['./movielist.component.scss'],
  standalone: true,
    imports: [
        MatTableModule,
        MatCardModule,
        MatDatepickerModule,
        MatNativeDateModule,
        NgIf,
        MatPaginatorModule],
})
export class MovielistComponent implements OnInit, AfterViewInit{

  screeningService = inject(ScreeningService);

  selected : Date = new Date();

  router = inject(Router);

  movies: Movie[] = [];
  dataSource = new MatTableDataSource<Movie>();

  displayedColumns: string[] = ['id', 'title', 'duration', 'description', 'minimalAge'];
  moviesSubject = new BehaviorSubject<Movie[]>([]);

  @ViewChild(MatPaginator) paginator !: MatPaginator;


  ngOnInit(): void {
      this.getMovies();
      this.moviesSubject.subscribe(movies => {
         this.movies = movies;
         this.dataSource.data = movies;
      });
  }

  ngAfterViewInit() {
      this.moviesSubject.subscribe(movies => {
          this.dataSource.paginator = this.paginator;
          this.paginator.showFirstLastButtons=true;
      })
  }

    dateChanged() {
    this.selected.setHours(10, 0);
    this.getMovies();
  }

  private getMovies() {
      this.screeningService.getScreeningsForGivenDate(this.selected).pipe(
          map(screenings => screenings.map(screening => screening.movie)),
          map(movies => {
              const uniques = new Set<number>;
              return movies.filter(movie => {
                      if (uniques.has(movie.movieId)) {
                          return false;
                      }
                      uniques.add(movie.movieId);
                      return true;
                  }
              )
          })
        ).subscribe(movies => {
            this.moviesSubject.next(movies);
      });
  }

    rowClicked(row: Movie) {
        this.showMovieDetails(row.movieId);
  }

  showMovieDetails(movieId: number) {
    this.router.navigate(['/home/movie_details'], { queryParams: { id: movieId } });
  }
}
