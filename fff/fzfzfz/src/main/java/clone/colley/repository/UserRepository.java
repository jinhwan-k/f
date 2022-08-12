package clone.colley.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import clone.colley.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String nickname);
}
