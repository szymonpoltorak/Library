import { Component } from '@angular/core';
import { Movie } from '@core/data/home/movie';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { MovieDetailsService } from '@core/services/movie_details/movie-details.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Screening } from '@core/data/home/screening';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { NGX_MAT_DATE_FORMATS, NgxMatDateFormats, NgxMatDatetimePickerModule, NgxMatNativeDateModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
    selector: 'app-movie-details',
    templateUrl: './movie-details.component.html',
    styleUrls: ['./movie-details.component.scss'],
    standalone: true,
    imports: [
        HttpClientModule,
        MatDatepickerModule,
        MatInputModule,
        NgxMatTimepickerModule,
        FormsModule,
        ReactiveFormsModule,
        MatButtonModule,
        NgxMatDatetimePickerModule,MatProgressSpinnerModule,
        NgxMatTimepickerModule, CommonModule, MatButtonModule,
        NgxMatNativeDateModule,  MatCardModule,MatTableModule, MatCardModule, MatDatepickerModule, MatNativeDateModule]
})
export class MovieDetailsComponent {

    private id: number | undefined;

    protected movie: Movie | undefined;
    protected screenings: Screening[] | undefined;

    protected date : Date = new Date();
    protected time : Date = new Date();

    constructor(
        protected readonly movieDetailsService: MovieDetailsService,
        protected readonly route: ActivatedRoute,
        protected readonly router: Router,
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
        if(time == undefined)
            time = "10:00";
        const timeParts = time.split(':');
        const formattedTime = timeParts.slice(0, timeParts.length - 1).join(':');
        return formattedTime;
    }
  
    protected return() {
        this.router.navigate(['/home/movies']);
    }
  
    protected add() {
        let dateAndTime = new Date();
        dateAndTime.setFullYear(this.date.getFullYear());
        dateAndTime.setMonth(this.date.getMonth());
        dateAndTime.setDate(this.date.getDate());
        dateAndTime.setHours(this.time.getHours());
        dateAndTime.setMinutes(this.time.getMinutes());
        this.movieDetailsService.createScreening(dateAndTime, this.movie!!.movieId).subscribe(screening => {
            this.screenings?.push(screening);
        })
    }

}
