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
public class BenchmarkTokenRunner extends AbstractBenchmark{
    private static UserRepository userRepository;
    private static TokenRepository tokenRepository;
    private static TokenManagerService tokenManagerService;
    private static JwtService jwtService;
    private static final String LOGMAIL = "logusername@mail.com";
    private static final String PASSWORD = "kicia.?312312312As";
    private static PasswordEncoder passwordEncoder;

    @Autowired
    void setContext(JwtService jwtService,TokenManagerService tokenManagerService, UserRepository userRepository,TokenRepository tokenRepository,PasswordEncoder passwordEncoder) {
        BenchmarkTokenRunner.userRepository = userRepository;
        BenchmarkTokenRunner.tokenRepository = tokenRepository;
        BenchmarkTokenRunner.passwordEncoder = passwordEncoder;
        BenchmarkTokenRunner.tokenManagerService = tokenManagerService;
        BenchmarkTokenRunner.jwtService = jwtService;
    }

    private User admin;
    private User user;
    private String generatedToken;




    @Setup(Level.Trial)
    public void setBenchmark(){

        admin = User.builder()
                .name("Jakub")
                .password(passwordEncoder.encode(PASSWORD))
                .username(LOGMAIL)
                .surname("Wysocki")
                .userRole(UserRole.ADMIN)
                .build();

        userRepository.saveAndFlush(admin);
        user = userRepository.findByUsername(LOGMAIL)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
        generatedToken = jwtService.generateToken(user);
    }

    @TearDown(Level.Invocation)
    public void clear(){
        tokenRepository.deleteAll();
    }
    @TearDown(Level.Trial)
    public void clearusers(){
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Benchmark
    public void saveUsersTokenBenchmark() {
        tokenManagerService.saveUsersToken(generatedToken,LOGMAIL);
    }

    @Benchmark
    public void saveUsersTokenUserBenchmark() {
        tokenManagerService.saveUsersToken(generatedToken,user);
    }

}
