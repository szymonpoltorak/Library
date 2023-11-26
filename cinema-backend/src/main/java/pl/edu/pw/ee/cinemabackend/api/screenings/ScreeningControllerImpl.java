package pl.edu.pw.ee.cinemabackend.api.screenings;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningRequest;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningResponse;
import pl.edu.pw.ee.cinemabackend.api.screenings.interfaces.ScreeningController;
import pl.edu.pw.ee.cinemabackend.api.screenings.interfaces.ScreeningService;
import pl.edu.pw.ee.cinemabackend.entities.user.User;

import java.time.LocalDate;
import java.util.List;

import static pl.edu.pw.ee.cinemabackend.api.screenings.costants.ScreeningMappings.API_SCREENINGS_MAPPING;
import static pl.edu.pw.ee.cinemabackend.api.screenings.costants.ScreeningMappings.CREATE_SCREENING_MAPPING;
import static pl.edu.pw.ee.cinemabackend.api.screenings.costants.ScreeningMappings.GET_SCREENINGS_FOR_DAY_MAPPING;
import static pl.edu.pw.ee.cinemabackend.api.screenings.costants.ScreeningMappings.GET_SCREENING_DETAILS_MAPPING;


@RestController
@RequestMapping(API_SCREENINGS_MAPPING)
@RequiredArgsConstructor
public class ScreeningControllerImpl implements ScreeningController {
    private final ScreeningService screeningService;

    @Override
    @GetMapping(value = GET_SCREENINGS_FOR_DAY_MAPPING)
    public final List<ScreeningResponse> getScreeningsForGivenDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return screeningService.getScreeningsForGivenDay(date);
    }

    @Override
    @GetMapping(value = GET_SCREENING_DETAILS_MAPPING)
    public final ScreeningResponse getScreeningDetails(@RequestParam long screeningId) {
        return screeningService.getScreeningDetails(screeningId);
    }

    @Override
    @PostMapping(value = CREATE_SCREENING_MAPPING)
    public final ScreeningResponse createScreening(@Valid @RequestBody ScreeningRequest screeningRequest,
                                                   @AuthenticationPrincipal User user) {
        return screeningService.createScreening(screeningRequest, user);
    }
}
