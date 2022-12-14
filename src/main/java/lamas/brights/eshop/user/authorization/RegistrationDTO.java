package lamas.brights.eshop.user.authorization;

public record RegistrationDTO(String firstName, String lastName, String email, String password, String repeatPassword) {

}
