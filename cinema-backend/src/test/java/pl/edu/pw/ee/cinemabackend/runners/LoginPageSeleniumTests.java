package pl.edu.pw.ee.cinemabackend.runners;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.edu.pw.ee.cinemabackend.config.selenium.WebDriverConfig;
import pl.edu.pw.ee.cinemabackend.pages.LoginPage;
import pl.edu.pw.ee.cinemabackend.pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:selenium-config.yml")
class LoginPageSeleniumTests {

    @Value("${baseUrl}")
    private String baseUrl;
    @Value("${browser}")
    private String browser;
    @Autowired
    private WebDriverConfig webDriverConfig;
    private LoginPage loginPage;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = webDriverConfig.setUpWebDriver(browser);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
    }

    @Test
    void whenOnMainPage_CheckIfTitleIsCorrect() {
        String pageTitle = driver.getTitle();
        String expected = "CinemaFrontend";

        assertEquals(pageTitle, expected,
                String.format("Expected value: %s", expected));
    }

    @Test
    void checkIfRedirectsToRegisterPage(){
        loginPage.clickRegister();
        RegisterPage registerPage = new RegisterPage(driver);

        String expectedTitle = "Cinema Register";

        assertEquals(registerPage.getPageTitle().getText(), expectedTitle);
    }

    @Test
    void shouldDisplayErrorMessages_whenClickedOnFields() {
        loginPage.getTxtEmail().click();
        loginPage.getTxtPassword().click();
        loginPage.getTxtEmail().click();

        String expectedEmailErrorMsg = "Email is required";
        String expectedPasswordMessage = "Password is required";


        assertEquals(loginPage.getEmailErrorMsg().getText(), expectedEmailErrorMsg);
        assertEquals(loginPage.getPasswordErrorMsg().getText(), expectedPasswordMessage);
    }

    @Test
    void shouldDisplayErrorMessages_whenGivenIncorrectInputs() {
        loginPage.getTxtEmail().sendKeys("abc");
        loginPage.getTxtPassword().sendKeys("abc");
        loginPage.getTxtEmail().click();

        String expectedEmailErrorMsg = "Please enter a valid email address";
        String expectedPasswordMessage = "Please enter a valid password address";


        assertEquals(loginPage.getEmailErrorMsg().getText(), expectedEmailErrorMsg);
        assertEquals(loginPage.getPasswordErrorMsg().getText(), expectedPasswordMessage);
    }

    @Test
    void shouldLogin_whenGivenCorrectCredentials(){
        //TODO
        assert true;
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
