package pl.edu.pw.ee.cinemabackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieRequest;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningRequest;
import pl.edu.pw.ee.cinemabackend.api.screenings.interfaces.ScreeningService;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.entities.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CinemaBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CinemaBackendApplication.class, args);
    }


    @Bean
    public CommandLineRunner initTestData(MovieService ms, ScreeningService ss, UserRepository ur, PasswordEncoder pe) {
        return (clr) -> {
            User admin = createAdmin(ur, pe);
            createUser(ur, pe);

            List<MovieRequest> movieRequests = generateMovies();
            Random random = new Random(0);
            LocalDate now = LocalDate.now();
            for (MovieRequest mr : movieRequests) {
                long mid = ms.createMovie(mr, admin).movieId();
                int c = random.nextInt(12)+1;
                int cm = -random.nextInt(12)-1;
                int d = random.nextInt(3)+2;
                for (int i = cm; i < c; i++) {
                    if (random.nextDouble() < 0.3) {
                        continue;
                    }
                    for (int j = 0; j < d; j++) {
                        ss.createScreening(new ScreeningRequest(mid,
                                now.plusDays(i),
                                LocalTime.of(10, 0).plusHours(j).plusMinutes(15*random.nextInt(4))
                        ), admin);
                    }
                }
            }
        };
    }

    private static final String[] TITLES = {
            "The Matrix", "Inception", "Star Wars: Episode IV", "Jurassic Park", "The Shawshank Redemption",
            "The Godfather", "Titanic", "Avatar", "Forrest Gump", "The Dark Knight",
            "Pulp Fiction", "Fight Club", "The Lord of the Rings: The Fellowship of the Ring", "The Avengers", "The Lion King",
            "Schindler's List", "E.T. the Extra-Terrestrial", "The Silence of the Lambs", "Back to the Future", "Gladiator"
    };
    private static final String[] DESCRIPTIONS = {
            "A heart-pounding action-packed thriller that will keep you on the edge of your seat with non-stop excitement and jaw-dropping stunts.",
            "Prepare to have your mind blown by this mind-bending sci-fi masterpiece that challenges the boundaries of reality and perception.",
            "Embark on an epic space adventure as you journey through the vast reaches of the galaxy, encountering strange worlds and thrilling encounters.",
            "Join the ultimate dinosaur-filled adventure, where prehistoric giants roam the Earth, and the survival of the fittest takes center stage.",
            "Experience a tale of drama and redemption that will tug at your heartstrings and leave you contemplating the meaning of life and second chances.",
            "Immerse yourself in an epic crime drama that unfolds a gripping narrative of power, betrayal, and the pursuit of justice in the gritty underworld.",
            "Indulge in a romantic drama that explores the complexities of love, relationships, and the enduring power of the human heart.",
            "Dive into an epic science fiction saga that transports you to distant worlds, introducing advanced technology, and exploring the mysteries of the cosmos.",
            "Warm your heart with this heartwarming drama that weaves a touching story of love, friendship, and the triumph of the human spirit.",
            "Gear up for superhero action like never before, with jaw-dropping special effects, heroic feats, and a battle between good and evil that will leave you breathless.",
            "Embark on a quirky crime drama filled with unexpected twists, eccentric characters, and a rollercoaster of suspense that will keep you guessing until the very end.",
            "Delve into the depths of the human mind with this psychological thriller, exploring the intricacies of the psyche and the thin line between sanity and madness.",
            "Enter a world of fantasy and adventure, where mythical creatures, magical realms, and epic quests unfold in a tale that captivates the imagination.",
            "Witness the gathering of superheroes in an ensemble like no other, as they unite to face a common threat and save the world from impending doom.",
            "Revisit an animated classic that has enchanted audiences of all ages, with timeless characters, memorable songs, and a tale that transcends generations.",
            "Step back in time with this historical drama, reliving pivotal moments in the past, and gaining insight into the lives of those who shaped the course of history.",
            "Experience a sci-fi family classic that takes you on a journey through time and space, blending adventure, humor, and heartwarming moments for the whole family.",
            "Feel the tension rise in this suspenseful thriller, where every moment counts, and the unexpected twists will keep you guessing until the very end.",
            "Embark on a hilarious time-travel comedy that will have you laughing out loud as characters navigate the challenges of temporal paradoxes and comedic chaos.",
            "Immerse yourself in an epic historical drama that unfolds the tapestry of history, with larger-than-life characters and events that shaped the course of civilizations."
    };
    private static final String[] DURATIONS = {"2h 30m", "2h 22m", "2h 1m", "2h 7m", "2h 22m", "3h 9m", "3h 15m", "2h 42m", "2h 22m", "2h 32m",
            "2h 34m", "2h 19m", "2h 58m", "2h 23m", "1h 29m", "3h 15m", "1h 55m", "1h 58m", "1h 56m", "2h 35m"};

    private static final int[] MIN_AGE = {12, 14, 8, 10, 16, 18, 12, 13, 10, 15, 17, 18, 13, 12, 6, 18, 8, 15, 12, 16};

    public List<MovieRequest> generateMovies() {
        ArrayList<MovieRequest> list = new ArrayList<>();
        for (int i = 0; i < TITLES.length; i++) {
            list.add(new MovieRequest(TITLES[i], DESCRIPTIONS[i], DURATIONS[i], MIN_AGE[i]));
        }
        return list;
    }

    private User createAdmin(UserRepository ur, PasswordEncoder pe) {
        User admin = User.builder()
                .username("admin123@mail.pl")
                .name("Pan")
                .surname("Admin")
                .password(pe.encode("admin"))
                .userRole(UserRole.ADMIN)
                .build();
        return ur.save(admin);
    }

    private User createUser(UserRepository ur, PasswordEncoder pe) {
        User user = User.builder()
                .username("user123@mail.pl")
                .name("Pan")
                .surname("Uzytkownik")
                .password(pe.encode("user"))
                .userRole(UserRole.USER)
                .build();
        return ur.save(user);
    }
}
