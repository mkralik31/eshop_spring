package lamas.brights.eshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String status;
    private String token;

    public LoginResponseDto(String status, String token) {
        this.status = status;
        this.token = token;
    }
}
