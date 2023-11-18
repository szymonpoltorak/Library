package pl.edu.pw.ee.cinemabackend;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty"},
        glue = {"pl.edu.pw.ee.cinemabackend.steps"}
)
public class CucumberRunnerTest {
}
