import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '@core/data/home/movie';
import { Screening } from '@core/data/home/screening';
import { ScreeningRequest } from '@core/data/home/screening-request';
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

    createScreening(dateAndTime: Date, movieId: number): Observable<Screening> {
        let yearStr = `${dateAndTime.getFullYear()}`
        let monthStr = `${dateAndTime.getMonth()+1}`
        if(monthStr.length == 1) {
            monthStr = "0"+monthStr;
        }
        let dayStr = `${dateAndTime.getDate()}`
        if(dayStr.length == 1) {
            dayStr = "0"+dayStr;
        }
        let hoursStr = `${dateAndTime.getHours()}`
        if(hoursStr.length == 1) {
            hoursStr = "0"+hoursStr;
        }
        let minutesStr = `${dateAndTime.getMinutes()}`
        if(minutesStr.length == 1) {
            minutesStr = "0"+minutesStr;
        }
        const timeStr = `${hoursStr}:${minutesStr}:00`;
        const dateStr = `${yearStr}-${monthStr}-${dayStr}`;
        const request: ScreeningRequest = {
            movieId: movieId,
            dayOfScreening: dateStr,
            hourOfScreening: timeStr
        };
        return this.http.post<Screening>(environment.httpBackend+"/api/screenings/createScreening", request)
            .pipe(catchError(() => of(JSON.parse(AuthApiCalls.ERROR_FOUND))));
    }

}
