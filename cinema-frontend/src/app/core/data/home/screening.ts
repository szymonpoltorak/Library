import {Movie} from "@data/home/movie";

export interface Screening {
    movie: Movie,

    dateOfScreening: string,

    hourOfScreening: string,
}