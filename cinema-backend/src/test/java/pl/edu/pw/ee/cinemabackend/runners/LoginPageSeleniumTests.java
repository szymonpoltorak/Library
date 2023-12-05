package pl.edu.pw.ee.cinemabackend.runners;

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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.HOME_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.LOGIN_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.REGISTER_URL;

@SpringBootTest
@TestPropertySource(locations = "classpath:selenium-config.yml")
class LoginPageSeleniumTests {

    @Value("${browser}")
    private String browser;
    @Autowired
    private WebDriverConfig webDriverConfig;
    private LoginPage loginPage;
    private WebDriver driver;

    @BeforeEach
    final void setup() {
        driver = webDriverConfig.setUpWebDriver(browser);
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
    }

    @Test
    final void whenOnMainPage_CheckIfTitleIsCorrect() {
        String pageTitle = driver.getTitle();
        String expected = "CinemaFrontend";

        assertEquals(expected, pageTitle,
                String.format("Expected value: %s", expected));
    }

    @Test
    final void checkIfRedirectsToRegisterPage() {
        loginPage.clickRegister();

        assertEquals(REGISTER_URL, driver.getCurrentUrl(),
                String.format("Expected value: %s", REGISTER_URL));
    }

    @Test
    final void shouldDisplayErrorMessages_whenClickedOnFields() {
        loginPage.getTxtEmail().click();
        loginPage.getTxtPassword().click();
        loginPage.getTxtEmail().click();

        String expectedEmailErrorMsg = "Email is required";
        String expectedPasswordMessage = "Password is required";

        assertAll(() -> assertEquals(expectedEmailErrorMsg, loginPage.getEmailErrorMsg().getText()),
                () -> assertEquals(expectedPasswordMessage, loginPage.getPasswordErrorMsg().getText()));
    }

    @Test
    final void shouldDisplayErrorMessages_whenGivenIncorrectInputs() {
        loginPage.getTxtEmail().sendKeys("abc");
        loginPage.getTxtPassword().sendKeys("abc");
        loginPage.getTxtEmail().click();

        String expectedEmailErrorMsg = "Please enter a valid email address";
        String expectedPasswordMessage = "Please enter a valid password address";

        assertAll(() -> assertEquals(expectedEmailErrorMsg, loginPage.getEmailErrorMsg().getText()),
                () -> assertEquals(expectedPasswordMessage, loginPage.getPasswordErrorMsg().getText()));
    }

    @Test
    final void shouldLogin_whenGivenCorrectCredentials() {
        loginPage.getTxtEmail().sendKeys("01231234@gmail.com");
        loginPage.getTxtPassword().sendKeys("#Silnehaslo123");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(HOME_URL));

        assertEquals(HOME_URL, driver.getCurrentUrl(),
                String.format("Expected value: %s", HOME_URL));
    }

    @AfterEach
    final void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
