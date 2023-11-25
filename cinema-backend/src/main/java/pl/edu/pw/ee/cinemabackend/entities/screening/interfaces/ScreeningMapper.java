package pl.edu.pw.ee.cinemabackend.entities.screening.interfaces;

import org.mapstruct.Mapper;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningResponse;
import pl.edu.pw.ee.cinemabackend.entities.screening.Screening;

@Mapper(componentModel = "spring")
public interface ScreeningMapper {

    ScreeningResponse mapToScreeningResponse(Screening screening);
}
