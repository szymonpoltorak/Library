import {Movie} from "@data/home/movie";

export interface Screening {
    movie: Movie,

    dayOfScreening: string,

    hourOfScreening: string,
}