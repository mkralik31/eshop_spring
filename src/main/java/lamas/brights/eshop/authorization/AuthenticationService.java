package lamas.brights.eshop.authorization;

import lamas.brights.eshop.user.User;

public interface AuthenticationService {

    AuthenticationToken getToken(User user);

    User getUser(String token);


    void authenticate(String token) throws Exception;

    void saveConfirmationToken(AuthenticationToken authenticationToken);
}
