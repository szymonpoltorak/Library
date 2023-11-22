package pl.edu.pw.ee.cinemabackend.api.auth.interfaces;


import pl.edu.pw.ee.cinemabackend.api.auth.data.*;

public interface AuthService {
    AuthResponse register(RegisterRequest userRequest);

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refreshToken(String refreshToken);

    TokenResponse validateUsersTokens(TokenRequest request);
}
