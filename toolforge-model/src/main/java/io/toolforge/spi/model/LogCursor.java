package io.toolforge.spi.model;

import static java.util.Objects.requireNonNull;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;

public class LogCursor {
  @JsonCreator
  public static LogCursor fromString(String s) {
    return of(s);
  }

  public static LogCursor of(String token) {
    return new LogCursor(token);
  }

  private final String token;

  public LogCursor(String token) {
    this.token = requireNonNull(token);
  }

  /**
   * @return the token
   */
  public String getToken() {
    return token;
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    LogCursor other = (LogCursor) obj;
    return Objects.equals(token, other.token);
  }

  @Override
  public String toString() {
    return getToken();
  }
}
