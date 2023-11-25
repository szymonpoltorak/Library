package pl.edu.pw.ee.cinemabackend.entities.screening.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pw.ee.cinemabackend.entities.screening.Screening;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("select s from Screening as s where s.dayOfScreening = :date")
    List<Screening> getScreeningsForGivenDay(LocalDate date);
}
