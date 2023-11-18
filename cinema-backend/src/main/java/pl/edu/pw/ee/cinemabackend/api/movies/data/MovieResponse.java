package pl.edu.pw.ee.cinemabackend.api.movies.data;

import lombok.Builder;

@Builder
public record MovieResponse(long movieId, String title, String description, String timeDuration, int minimalAge) {
}
