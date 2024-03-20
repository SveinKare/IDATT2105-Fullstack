package ntnu.fullstack.no.calculatorbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class User {
  @Id
  @Size(max=50)
  private String username;
  @Column
  @NotNull
  @Size(max=255)
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(@NotEmpty String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(@NotEmpty String password) {
    this.password = password;
  }
}
