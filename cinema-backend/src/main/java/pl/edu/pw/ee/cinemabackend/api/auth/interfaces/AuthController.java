package pl.edu.pw.ee.cinemabackend.api.auth.interfaces;


import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.api.auth.data.LoginRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.data.RegisterRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.data.TokenRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.data.TokenResponse;

public interface AuthController {
    AuthResponse registerUser(RegisterRequest registerRequest);

    AuthResponse loginUser(LoginRequest loginRequest);

    AuthResponse refreshUserToken(String refreshToken);

    TokenResponse authenticateUser(TokenRequest request);
}
