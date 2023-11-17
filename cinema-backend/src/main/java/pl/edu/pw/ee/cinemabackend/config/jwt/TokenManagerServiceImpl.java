package pl.edu.pw.ee.cinemabackend.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.config.jwt.interfaces.JwtService;
import pl.edu.pw.ee.cinemabackend.config.jwt.interfaces.TokenManagerService;
import pl.edu.pw.ee.cinemabackend.entities.token.JwtToken;
import pl.edu.pw.ee.cinemabackend.entities.token.TokenType;
import pl.edu.pw.ee.cinemabackend.entities.token.interfaces.TokenRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.interfaces.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenManagerServiceImpl implements TokenManagerService {
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public final void saveUsersToken(String jwtToken, User user) {
        tokenRepository.save(buildToken(jwtToken, user));
    }

    @Override
    public final void saveUsersToken(String jwtToken, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        saveUsersToken(jwtToken, user);
    }

    @Override
    public final AuthResponse buildTokensIntoResponse(String authToken, String refreshToken) {
        return buildResponse(authToken, refreshToken);
    }

    @Override
    public final AuthResponse buildTokensIntoResponse(User user, boolean shouldBeRevoked) {
        String authToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        if (shouldBeRevoked) {
            revokeUserTokens(user);
        }
        saveUsersToken(authToken, user);

        return buildResponse(authToken, refreshToken);
    }

    @Override
    public final void revokeUserTokens(User user) {
        List<JwtToken> userTokens = tokenRepository.findAllValidTokensByUserId(user.getUserId());

        if (userTokens.isEmpty()) {
            return;
        }

        userTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(userTokens);
    }

    @Override
    public final void revokeUserTokens(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        revokeUserTokens(user);
    }

    private AuthResponse buildResponse(String authToken, String refreshToken) {
        return AuthResponse.builder()
                .authToken(authToken)
                .refreshToken(refreshToken)
                .build();
    }

    private JwtToken buildToken(String jwtToken, User user) {
        return JwtToken.builder()
                .token(jwtToken)
                .tokenType(TokenType.JWT_BEARER_TOKEN)
                .user(user)
                .build();
    }
}
