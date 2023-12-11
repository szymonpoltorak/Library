package pl.edu.pw.ee.cinemabackend.jmh;

import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.api.auth.data.LoginRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.interfaces.AuthService;
import pl.edu.pw.ee.cinemabackend.config.jwt.interfaces.TokenManagerService;
import pl.edu.pw.ee.cinemabackend.entities.token.interfaces.TokenRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.entities.user.interfaces.UserRepository;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BenchmarkTokenManagerRunner extends AbstractBenchmark {
    private static AuthService authService;
    private static UserRepository userRepository;
    private static TokenRepository tokenRepository;
    private static TokenManagerService tokenManagerService;
    private static final String LOGMAIL = "logusername@mail.com";
    private static final String PASSWORD = "kicia.?312312312As";
    private static PasswordEncoder passwordEncoder;
    private LoginRequest loginRequest;
    private AuthResponse authResponse;
    private User admin;
    private User user;

    @Autowired
    public final void setContext(AuthService authService, TokenManagerService tokenManagerService, UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        BenchmarkTokenManagerRunner.authService = authService;
        BenchmarkTokenManagerRunner.userRepository = userRepository;
        BenchmarkTokenManagerRunner.tokenRepository = tokenRepository;
        BenchmarkTokenManagerRunner.passwordEncoder = passwordEncoder;
        BenchmarkTokenManagerRunner.tokenManagerService = tokenManagerService;
    }

    @Setup(Level.Trial)
    public final void setBenchmark() {

        admin = User.builder()
                .name("Jakub")
                .password(passwordEncoder.encode(PASSWORD))
                .username(LOGMAIL)
                .surname("Wysocki")
                .userRole(UserRole.ADMIN)
                .build();

        loginRequest = LoginRequest
                .builder()
                .username(LOGMAIL)
                .password(PASSWORD)
                .build();
        user = User.builder()
                .name("Jakub")
                .password(PASSWORD)
                .username(LOGMAIL)
                .surname("Wysocki")
                .userRole(UserRole.ADMIN)
                .build();
        userRepository.saveAndFlush(admin);
        authResponse = authService.login(loginRequest);
    }

    @TearDown(Level.Trial)
    public final void clear() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Benchmark
    public final void revokeUserTokensUserBenchmark() {
        tokenManagerService.revokeUserTokens(user);
    }

    @Benchmark
    public final void revokeUserTokensUserNameBenchmark() {
        tokenManagerService.revokeUserTokens(LOGMAIL);
    }

    @Benchmark
    public final void buildTokensIntoResponseenchmark() {
        tokenManagerService.buildTokensIntoResponse(authResponse.authToken(), authResponse.refreshToken());
    }

}
