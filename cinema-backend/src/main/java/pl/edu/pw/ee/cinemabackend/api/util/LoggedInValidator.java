package pl.edu.pw.ee.cinemabackend.api.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;

@Component
public class LoggedInValidator {

    public void checkIfUserIsLoggedIn(User user) {
        if (user.getUserRole().equals(UserRole.USER)) {
            throw new AccessDeniedException("Signed in user must be an admin");
        }
    }

}
