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
import pl.edu.pw.ee.cinemabackend.pages.MovieDetailsPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.HOME_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.LOGIN_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.MOVIES_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.MOVIE_DETAILS_URL;
import static pl.edu.pw.ee.cinemabackend.selenium.constants.TestConstants.TIMEOUT;

@SpringBootTest
@TestPropertySource(locations = "classpath:selenium-config.yml")
class MovieDetailsSeleniumTests {

    @Value("${browser}")
    private String browser;
    @Autowired
    private WebDriverConfig webDriverConfig;
    private MovieDetailsPage movieDetailsPage;
    private WebDriver driver;

    @BeforeEach
    final void setup() {
        driver = webDriverConfig.setUpWebDriver(browser);
        driver.get(LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        signIn(loginPage);
        String firstMovieDetailsUrl = String.format("%s%s", MOVIE_DETAILS_URL, 1);
        driver.navigate().to(firstMovieDetailsUrl);
        movieDetailsPage = new MovieDetailsPage(driver);
    }

    @Test
    final void shouldReturnToMovies_whenClickedReturn(){
        movieDetailsPage.clickReturnButton();
        assertEquals(MOVIES_URL, driver.getCurrentUrl(),
                String.format("Expected value: %s", MOVIES_URL));
    }

    @Test
    final void shouldAddScreening_whenClickedAddButton(){
        movieDetailsPage.clickCalendar();
        movieDetailsPage.clickFutureYear();
        movieDetailsPage.clickFutureMonth();
        movieDetailsPage.clickFutureDay();
        movieDetailsPage.clickAddButton();

        driver.navigate().refresh();

        String expectedDate = "Dec 21, 2023";
        assertEquals(expectedDate, movieDetailsPage.getAddedScreening().getText(),
                String.format("Expected value: %s", expectedDate));
    }

    @Test
    final void shouldReturnToHomePage_whenClickedHomeButton(){
        movieDetailsPage.clickHomeButton();

        assertEquals(HOME_URL, driver.getCurrentUrl(),
                String.format("Expected value: %s", HOME_URL));
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
}

