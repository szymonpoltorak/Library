package pl.edu.pw.ee.cinemabackend.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.edu.pw.ee.cinemabackend.config.selenium.WebDriverConfig;
import pl.edu.pw.ee.cinemabackend.pages.LoginPage;
import pl.edu.pw.ee.cinemabackend.pages.MoviesPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.HOME_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.LOGIN_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.MOVIES_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.MOVIE_DETAILS_URL;
import static pl.edu.pw.ee.cinemabackend.selenium.constants.TestConstants.TIMEOUT;

@SpringBootTest
@TestPropertySource(locations = "classpath:selenium-config.yml")
class MoviesPageSeleniumTests {

    @Value("${browser}")
    private String browser;
    @Autowired
    private WebDriverConfig webDriverConfig;
    private MoviesPage moviesPage;
    private WebDriver driver;
    private final Pattern pattern = Pattern.compile("\\d+");

    @BeforeEach
    final void setup() {
        driver = webDriverConfig.setUpWebDriver(browser);
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        signIn(loginPage);
        driver.navigate().to(MOVIES_URL);
        moviesPage = new MoviesPage(driver);
    }

    @Test
    final void shouldRedirectToMovieDetails_whenClickedOnAMovie() {
        long movieId = Long.parseLong(moviesPage.getFirstMovieId().getText());
        String expectedMovieDetailsUrl = String.format("%s%s", MOVIE_DETAILS_URL, movieId);

        moviesPage.clickFirstMovie();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.urlToBe(expectedMovieDetailsUrl));

        assertEquals(expectedMovieDetailsUrl, driver.getCurrentUrl(),
                String.format("Expected value: %s", expectedMovieDetailsUrl));
    }

    @Test
    final void shouldBeMaxFiveMoviesOnPage_whenRedirectedToIt() {
        List<Integer> integersInText = getIntegersFromText(moviesPage
                .getTxtMoviesOnPage()
                .getText());

        int expectedAmountOfMoviesOnPage;
        if (integersInText.get(2) >= 5) {
            expectedAmountOfMoviesOnPage = 5;
        } else {
            expectedAmountOfMoviesOnPage = integersInText.get(2);
        }

        int amountOfMoviesOnPage = integersInText.get(1);

        assertEquals(expectedAmountOfMoviesOnPage, amountOfMoviesOnPage,
                String.format("Expected value: %s", expectedAmountOfMoviesOnPage));
    }

    @Test
    final void shouldChangeAmountOfDisplayedMovies_whenClickedOnItemsPerPage() {

        moviesPage.clickItemsPerPageDrop();
        moviesPage.clickItemsPerPageTen();

        List<Integer> integersInText = getIntegersFromText(moviesPage
                .getTxtMoviesOnPage()
                .getText());

        int expectedAmountOfMoviesOnPage;
        if (integersInText.get(2) >= 10) {
            expectedAmountOfMoviesOnPage = 10;
        } else {
            expectedAmountOfMoviesOnPage = integersInText.get(2);
        }

        int amountOfMoviesOnPage = integersInText.get(1);

        assertEquals(expectedAmountOfMoviesOnPage, amountOfMoviesOnPage,
                String.format("Expected value: %s", expectedAmountOfMoviesOnPage));
    }

    @Test
    final void shouldBeNoMoviesOnPage_whenSetPastDate() {

        moviesPage.clickCalendar();
        moviesPage.clickPastYear();
        moviesPage.clickPastMonth();
        moviesPage.clickPastDay();

        List<Integer> integersInText = getIntegersFromText(moviesPage
                .getTxtMoviesOnPage()
                .getText());
        int expectedAmountOfMoviesOnPage = 0;

        integersInText.forEach(currentIntegerInExpression ->
                assertEquals(expectedAmountOfMoviesOnPage, currentIntegerInExpression,
                        String.format("Expected value: %s", expectedAmountOfMoviesOnPage)));
    }

    @Test
    final void shouldCorrectlyChangeMoviesOnPage_whenClickedArrow() {
        List<Integer> integersInText = getIntegersFromText(moviesPage
                .getTxtMoviesOnPage()
                .getText());

        int amountOfAllMoviesThisDay = integersInText.get(2);
        int expectedNumberOfFirstMovie = 1;
        int expectedNumberOfLastMovie = 5;
        while (amountOfAllMoviesThisDay - 5 >= 0) {
            assertEquals(expectedNumberOfFirstMovie, integersInText.get(0),
                    String.format("Expected value: %s", expectedNumberOfFirstMovie));
            assertEquals(expectedNumberOfLastMovie, integersInText.get(1),
                    String.format("Expected value: %s", expectedNumberOfLastMovie));
            moviesPage.clickNextPage();
            integersInText = getIntegersFromText(moviesPage
                    .getTxtMoviesOnPage()
                    .getText());
            expectedNumberOfFirstMovie+=5;
            expectedNumberOfLastMovie+=5;
            amountOfAllMoviesThisDay-=5;
        }
    }

    @AfterEach
    final void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void signIn(LoginPage loginPage) {
        loginPage.getTxtEmail().sendKeys("admin123@mail.pl");
        loginPage.getTxtPassword().sendKeys("admin");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.urlToBe(HOME_URL));
    }

    private List<Integer> getIntegersFromText(String text) {
        Matcher matcher = pattern.matcher(text);
        List<Integer> integersInText = new ArrayList<>();
        while (matcher.find()) {
            integersInText.add(Integer.parseInt(matcher.group()));
        }
        return integersInText;
    }

}
