package pl.edu.pw.ee.cinemabackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    @GetMapping("/version")
    public String getVersion() {
        return "1.0";
    }
}