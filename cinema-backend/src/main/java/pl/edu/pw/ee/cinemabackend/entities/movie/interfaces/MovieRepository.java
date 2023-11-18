package pl.edu.pw.ee.cinemabackend.entities.movie.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findAllByMoveId(Pageable pageable);
}
