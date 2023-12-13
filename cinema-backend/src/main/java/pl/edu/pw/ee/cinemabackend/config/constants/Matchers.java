package pl.edu.pw.ee.cinemabackend.config.constants;

public final class Matchers {
    public static final String AUTH_MAPPING = "/api/auth/";

    public static final String LOGOUT_URL = "/api/auth/logout";

    public static final String AUTH_MATCHERS = "/api/auth/**";
    public static final String API_MATCHERS = "/api/**";

    public static final String OAUTH2_LOGIN = "/login/oauth2/**";

    private Matchers() {
    }
}
