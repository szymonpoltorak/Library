package pl.edu.pw.ee.cinemabackend.config.constants;

public final class Properties {
    public static final String EXPIRATION_PROPERTY = "${spring.security.jwt.expiration-time}";

    public static final String ENCODING_KEY_PROPERTY = "${spring.security.jwt.encoding-key}";

    public static final String REFRESH_PROPERTY = "${spring.security.jwt.refresh-time}";

    private Properties() {
    }
}
