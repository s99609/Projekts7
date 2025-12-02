package pl.motoparts.motoparts_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motoparts.motoparts_server.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
