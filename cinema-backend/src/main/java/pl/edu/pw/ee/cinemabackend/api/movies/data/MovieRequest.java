package pl.edu.pw.ee.cinemabackend.api.movies.data;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@Builder
public record MovieRequest(String title, String description, String timeDuration, int minimalAge) {
}
