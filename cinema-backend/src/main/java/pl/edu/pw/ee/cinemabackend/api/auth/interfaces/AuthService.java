package pl.edu.pw.ee.cinemabackend.api.auth.interfaces;


import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.api.auth.data.LoginRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.data.RegisterRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.data.TokenRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.data.TokenResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest userRequest);

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refreshToken(String refreshToken);

    TokenResponse validateUsersTokens(TokenRequest request);
}
