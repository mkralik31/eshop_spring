package lamas.brights.eshop.authorization;

import lamas.brights.eshop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenRepository tokenRepository;

    @Autowired
    public AuthenticationServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }
    public AuthenticationToken getToken(User user) {
        return tokenRepository.findTokenByUser(user);
    }

    public User getUser(String token) {
        Optional<AuthenticationToken> _authenticationToken = tokenRepository.findTokenByToken(token);
        AuthenticationToken authenticationToken = null;

        if (_authenticationToken.isPresent()) {
            authenticationToken = _authenticationToken.get();
            return authenticationToken.getUser();
        }
        return null;
    }


    public void authenticate(String token) throws Exception {

        //throw exception only if TOKEN is not valid
        Optional<AuthenticationToken> _authenticationToken = tokenRepository.findTokenByToken(token);

        System.err.println(
                "\n" + "AuthenticationServiceImpl.authenticate" + "\n" +
                token + "\n" +
                getUser(token).getEmail() + "\n" );
        AuthenticationToken authenticationToken = null;
        // if TOKEN exists continue
        if (!_authenticationToken.isPresent()) {
            throw new Exception("AUTH_TOKEN_IS_NOT_PRESENT");
        }
        // if TOKEN has user continue
        if (getUser(token) == null) {
            throw new Exception("AUTH_TOKEN_IS_NOT_VALID");
        }
    }
}
