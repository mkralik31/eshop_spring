package lamas.brights.eshop.user.authorization;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.sql.Date;

public class Token {
    @Getter
    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public static Token of(Long userId, Long validityInMinutes, String secretKey) {
        Instant issueDate = Instant.now();
        return new Token(
                Jwts.builder()
                    .claim("userId", userId)
                    .setIssuedAt(Date.from(issueDate))
                    .setExpiration(Date.from(issueDate.plus(validityInMinutes, ChronoUnit.MINUTES)))
                    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .compact());

    }
}
