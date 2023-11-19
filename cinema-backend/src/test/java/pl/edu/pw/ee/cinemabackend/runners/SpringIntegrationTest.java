package pl.edu.pw.ee.cinemabackend.runners;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import pl.edu.pw.ee.cinemabackend.CinemaBackendApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = CinemaBackendApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
}