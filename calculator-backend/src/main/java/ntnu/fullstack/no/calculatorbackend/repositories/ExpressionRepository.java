package ntnu.fullstack.no.calculatorbackend.repositories;

import java.util.List;
import ntnu.fullstack.no.calculatorbackend.model.ExpressionEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpressionRepository extends JpaRepository<ExpressionEntry, ExpressionEntry> {
  List<ExpressionEntry> findByUsername(String username);
}
