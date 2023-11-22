package pl.edu.pw.ee.cinemabackend.api.movies.data;

import lombok.Builder;

@Builder
public record MovieRequest(String title, String description, String timeDuration, int minimalAge) {
}
