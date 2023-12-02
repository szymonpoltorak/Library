package pl.edu.pw.ee.cinemabackend.runners;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.edu.pw.ee.cinemabackend.config.WebDriverConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:selenium-config.yml")
class CinemaBackendApplicationTests {

    WebDriver driver;
    String TITLE_XPATH = "//a[@href='/']";
    @Value("${baseUrl}")
    private String baseUrl;
    @Value("${browser}")
    private String browser;
    @Autowired
    WebDriverConfig webDriverConfig;

    @BeforeEach
    void setup() {
        driver = webDriverConfig.setUpWebDriver(browser);
        driver.get(baseUrl);
    }

    @Test
    void whenOnMainPage_CheckIfTitleIsCorrect() {
        System.out.println(browser);
        String pageTitle = driver.getTitle();
        String expected = "CinemaFrontend";

        assertEquals(pageTitle, expected,
                String.format("Expected value: %s", expected));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
