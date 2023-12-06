import { Component } from '@angular/core';
import { Movie } from '@core/data/home/movie';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { MovieDetailsService } from '@core/services/movie_details/movie-details.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Screening } from '@core/data/home/screening';
import { MatButtonModule } from '@angular/material/button';

@Component({
    selector: 'app-movie-details',
    templateUrl: './movie-details.component.html',
    styleUrls: ['./movie-details.component.scss'],
    standalone: true,
    imports: [MatProgressSpinnerModule, CommonModule, MatButtonModule]
})
export class MovieDetailsComponent {

    private id: number | undefined;

    protected movie: Movie | undefined;
    protected screenings: Screening[] | undefined;

    constructor(
        protected readonly movieDetailsService: MovieDetailsService,
        protected readonly route: ActivatedRoute,
    ) { }

    ngOnInit() {
        this.route.queryParamMap.subscribe(params => {
            this.processQueryParameterChanges(params);
        });
    }

    private processQueryParameterChanges(params: ParamMap) {
        let previousId = this.id;
        let id = parseInt(params.get('id') + "", 10);
        if (!Number.isInteger(id)) {
            return;
        }
        this.id = id;
        if (this.id == previousId) {
            return;
        }

        this.reloadViewState();
    }

    private reloadViewState() {
        if (this.id == undefined) {
            this.movie = undefined;
            return;
        }
        this.movieDetailsService.getMovieDetails(this.id).subscribe(movie => {
            this.movie = movie;
        });
        this.movieDetailsService.getScreeningsForMovie(this.id).subscribe(screenings => {
            this.screenings = screenings;
        });
    }

    protected formatTime(time: string): string {
        const timeParts = time.split(':');
        const formattedTime = timeParts.slice(0, timeParts.length - 1).join(':');
        return formattedTime;
    }
  
    protected return() {
    }

}
