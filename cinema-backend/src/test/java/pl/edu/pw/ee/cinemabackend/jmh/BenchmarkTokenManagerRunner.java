package pl.edu.pw.ee.cinemabackend.jmh;

import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.api.auth.data.LoginRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.data.TokenRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.interfaces.AuthService;
import pl.edu.pw.ee.cinemabackend.config.jwt.interfaces.JwtService;
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
public class BenchmarkTokenManagerRunner extends AbstractBenchmark{
    private static AuthService authService;
    private static UserRepository userRepository;
    private static TokenRepository tokenRepository;
    private static TokenManagerService tokenManagerService;
    private static JwtService jwtService;
    private static final String LOGMAIL = "logusername@mail.com";
    private static final String PASSWORD = "kicia.?312312312As";
    private static PasswordEncoder passwordEncoder;

    @Autowired
    void setContext(AuthService authService,JwtService jwtService,TokenManagerService tokenManagerService, UserRepository userRepository,TokenRepository tokenRepository,PasswordEncoder passwordEncoder) {
        BenchmarkTokenManagerRunner.authService = authService;
        BenchmarkTokenManagerRunner.userRepository = userRepository;
        BenchmarkTokenManagerRunner.tokenRepository = tokenRepository;
        BenchmarkTokenManagerRunner.passwordEncoder = passwordEncoder;
        BenchmarkTokenManagerRunner.tokenManagerService = tokenManagerService;
        BenchmarkTokenManagerRunner.jwtService = jwtService;
    }

    private LoginRequest loginRequest;
    private AuthResponse authResponse;
    private User admin;
    private User user;



    @Setup(Level.Trial)
    public void setBenchmark(){

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
    public void clear(){
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }




    @Benchmark
    public void revokeUserTokensUserBenchmark() {
        tokenManagerService.revokeUserTokens(user);
    }
    @Benchmark
    public void revokeUserTokensUserNameBenchmark() {
        tokenManagerService.revokeUserTokens(LOGMAIL);
    }


    @Benchmark
    public void buildTokensIntoResponseenchmark() {
        tokenManagerService.buildTokensIntoResponse(authResponse.authToken(), authResponse.refreshToken());
    }


}
