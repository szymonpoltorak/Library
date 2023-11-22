package pl.edu.pw.ee.cinemabackend.api.movies.constants;

public final class ValidationConstants {
    public static final String INVALID_TITLE_MESSAGE = "Movie title cannot be null or blank";
    public static final String INVALID_DESCRIPTION_MESSAGE = "Movie description cannot be null or blank";
    public static final String INVALID_DURATION_MESSAGE = "Movie duration cannot be null or blank";
    public static final String INVALID_MIN_AGE_MESSAGE = "Age must be a positive integer";

    private ValidationConstants() {
    }
}
