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
import pl.edu.pw.ee.cinemabackend.config.selenium.SeleniumWebDriverConfig;
import pl.edu.pw.ee.cinemabackend.pages.RegisterPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.HOME_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.LOGIN_URL;
import static pl.edu.pw.ee.cinemabackend.pages.constants.PagesConstants.REGISTER_URL;
import static pl.edu.pw.ee.cinemabackend.selenium.constants.TestConstants.TIMEOUT;

@SpringBootTest
@TestPropertySource(locations = "classpath:selenium-config.yml")
class RegisterPageSeleniumTests {

    @Value("${browser}")
    private String browser;
    @Autowired
    private SeleniumWebDriverConfig seleniumWebDriverConfig;
    private RegisterPage registerPage;
    private WebDriver driver;

    @BeforeEach
    final void setup() {
        driver = seleniumWebDriverConfig.setUpWebDriver(browser);
        driver.get(REGISTER_URL);
        registerPage = new RegisterPage(driver);
    }

    @Test
    final void checkIfReturnRedirectsToLoginPage() {
        registerPage.clickReturn();

        assertEquals(LOGIN_URL, driver.getCurrentUrl(),
                String.format("Expected value: %s", LOGIN_URL));
    }

    @Test
    final void shouldDisplayErrorMessages_whenClickedOnFields() {
        registerPage.getTxtName().click();
        registerPage.getTxtSurname().click();
        registerPage.getTxtEmail().click();
        registerPage.getTxtPassword().click();
        registerPage.getTxtRepeatPassword().click();
        registerPage.getTxtName().click();

        String expectedNameErrorMsg = "Name is required";
        String expectedSurnameMessage = "Surname is required";
        String expectedEmailErrorMsg = "Email is required";
        String expectedPasswordMessage = "Password is required";
        String expectedRepeatPassword = "Password is required";

        assertAll(() -> assertEquals(expectedNameErrorMsg, registerPage.getNameErrorMsg().getText()),
                () -> assertEquals(expectedSurnameMessage, registerPage.getSurnameErrorMsg().getText()),
                () -> assertEquals(expectedEmailErrorMsg, registerPage.getEmailErrorMsg().getText()),
                () -> assertEquals(expectedPasswordMessage, registerPage.getPasswordErrorMsg().getText()),
                () -> assertEquals(expectedRepeatPassword, registerPage.getRepeatPasswordErrorMsg().getText()));
    }

    @Test
    final void shouldDisplayErrorMessages_whenGivenIncorrectInputs() {
        registerPage.getTxtEmail().sendKeys("abc");
        registerPage.getTxtPassword().sendKeys("abc");
        registerPage.getTxtRepeatPassword().sendKeys("abc");
        registerPage.getTxtName().click();

        String expectedEmailErrorMsg = "Please enter a valid email address";
        String expectedPasswordMessage = "Please enter a valid password address";
        String expectedRepeatPassword = "Please enter a valid password address";

        assertAll(() -> assertEquals(expectedEmailErrorMsg, registerPage.getEmailErrorMsg().getText()),
                () -> assertEquals(expectedPasswordMessage, registerPage.getPasswordErrorMsg().getText()),
                () -> assertEquals(expectedRepeatPassword, registerPage.getRepeatPasswordErrorMsg().getText()));
    }

    @Test
    final void shouldStayOnSite_whenGivenIncorrectInputAndClickedRegister() {
        registerPage.getTxtEmail().sendKeys("abc");
        registerPage.clickRegister();

        assertEquals(REGISTER_URL, driver.getCurrentUrl(),
                String.format("Expected value: %s", REGISTER_URL));
    }

    @Test
    final void shouldRedirect_whenGivenCorrectInputAndClickedRegister() {
        registerPage.getTxtName().sendKeys("Jakub");
        registerPage.getTxtSurname().sendKeys("Wadzi");
        registerPage.getTxtEmail().sendKeys("016963@gmail.com");
        registerPage.getTxtPassword().sendKeys("#Silnehaslo123");
        registerPage.getTxtRepeatPassword().sendKeys("#Silnehaslo123");
        registerPage.clickRegister();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
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
