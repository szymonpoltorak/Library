package pl.edu.pw.ee.cinemabackend.pages.constants;

public final class PagesConstants {

    private static final String BASE_URL = "http://localhost:4200/";
    public static final String REGISTER_URL = String.format("%s/auth/register", BASE_URL);
    public static final String LOGIN_URL = String.format("%s/auth/login", BASE_URL);
    public static final String HOME_URL = String.format("%s/home", BASE_URL);

    private PagesConstants() {
    }
}
