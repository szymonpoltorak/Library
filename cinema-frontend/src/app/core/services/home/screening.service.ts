import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Screening} from "@data/home/screening";
import {environment} from "@environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ScreeningService {

  constructor(private http: HttpClient) {

  }

  getScreeningsForGivenDate(date: Date) {
    const isoDate = date.toISOString().split('T')[0];
    console.log("Iso date: " + isoDate);

    return this.http.get<Screening[]>(`${environment.httpBackend}/api/screenings/getScreeningsForDay`, {
      params: {
        date: isoDate
      }
    })
  }
}
