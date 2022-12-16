package lamas.brights.eshop.authorization;

import lamas.brights.eshop.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findTokenByUser(User user);

    Optional<AuthenticationToken> findTokenByToken(String token);
}
