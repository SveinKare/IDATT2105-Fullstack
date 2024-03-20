package ntnu.fullstack.no.calculatorbackend.util;

public class ErrorResponse {
  private String title;
  private int status;
  private String timestamp;


  public ErrorResponse(String title, int status, String timestamp) {
    this.title = title;
    this.status = status;
    this.timestamp = timestamp;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}
