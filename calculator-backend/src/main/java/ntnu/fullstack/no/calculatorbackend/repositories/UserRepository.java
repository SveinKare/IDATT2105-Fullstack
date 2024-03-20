package ntnu.fullstack.no.calculatorbackend.repositories;

import java.util.Optional;
import ntnu.fullstack.no.calculatorbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUsername(String username);
}
