package pl.edu.pw.ee.cinemabackend.api.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;

@Component
public class LoggedInValidator {

    public void checkIfUserIsLoggedIn(){
        if (SecurityContextHolder.getContext().getAuthentication() == null){
            throw new AccessDeniedException("No user is signed in");
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getUserRole().equals(UserRole.USER)){
            throw new AccessDeniedException("Signed in user must be an admin");
        }
    }

}
