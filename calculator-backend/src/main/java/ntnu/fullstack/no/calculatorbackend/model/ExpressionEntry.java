package ntnu.fullstack.no.calculatorbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "history")
@IdClass(ExpressionEntry.class)
public class ExpressionEntry implements Serializable {
  @NotEmpty
  @Size(max=100)
  @Id
  private String expression;
  @NotEmpty
  @Size(max=50)
  @Id
  private String username;

  public String getExpression() {
    return expression;
  }

  public void setExpression(@NotEmpty String expression) {
    this.expression = expression;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(@NotEmpty String username) {
    this.username = username;
  }
}
