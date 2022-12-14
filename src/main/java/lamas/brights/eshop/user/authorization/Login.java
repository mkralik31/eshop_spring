package lamas.brights.eshop.user.authorization;

import lombok.Getter;

public class Login {
    @Getter
    private final Token accessToken;

    private Login(Token accessToken) {
        this.accessToken = accessToken;
    }

    public static Login of(Long userId, String accessSecret) {
        return new Login(
                Token.of(userId, 1L, accessSecret)
        );
    }

}
