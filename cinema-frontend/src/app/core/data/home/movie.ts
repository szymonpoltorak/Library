import {Screening} from "@data/home/screening";

export interface Movie {
    movieId: number,

    title: string,

    description: string,

    timeDuration: string,

    minimalAge: number,

    screenings: Screening[]
}