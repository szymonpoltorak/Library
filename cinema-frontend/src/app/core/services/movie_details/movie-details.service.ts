import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '@core/data/home/movie';
import { Screening } from '@core/data/home/screening';
import { AuthApiCalls } from '@enums/auth/AuthApiCalls';
import { environment } from "@environments/environment";
import { Observable, catchError, of } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class MovieDetailsService {
    constructor(private http: HttpClient) { }

    getMovieDetails(movieId: number): Observable<Movie> {
        return this.http.get<Movie>(environment.httpBackend+"/api/movies/getMovieDetails", {params: {movieId: movieId}})
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));
    }

    getScreeningsForMovie(movieId: number): Observable<Screening[]> {
        return this.http.get<Screening[]>(environment.httpBackend+"/api/screenings/getScreeningsForMovie", {params: {movieId: movieId}})
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));
    }

}
