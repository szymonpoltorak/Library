package pl.edu.pw.ee.cinemabackend.api.auth.data;

import lombok.Builder;

@Builder
public record LoginRequest(String username, String password) {
}
