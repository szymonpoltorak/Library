package pl.edu.pw.ee.cinemabackend.api.auth.interfaces;


import pl.edu.pw.ee.cinemabackend.api.auth.data.*;

public interface AuthController {
    AuthResponse registerUser(RegisterRequest registerRequest);

    AuthResponse loginUser(LoginRequest loginRequest);

    AuthResponse refreshUserToken(String refreshToken);

    TokenResponse authenticateUser(TokenRequest request);
}
