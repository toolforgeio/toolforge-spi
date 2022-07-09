package io.toolforge.spi.model;

import static java.util.Objects.requireNonNull;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class LogCursor {
  @JsonCreator
  public static LogCursor fromString(String s) {
    long millis =
        Long.parseLong(new String(Base64.getUrlDecoder().decode(s), StandardCharsets.UTF_8));
    return of(Instant.ofEpochMilli(millis).atOffset(ZoneOffset.UTC));
  }

  public static LogCursor of(OffsetDateTime timestamp) {
    return new LogCursor(timestamp);
  }

  private final OffsetDateTime timestamp;

  public LogCursor(OffsetDateTime timestamp) {
    this.timestamp = requireNonNull(timestamp);
  }

  /**
   * @return the timestamp
   */
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp);
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
    return timestamp == other.timestamp;
  }

  @Override
  @JsonValue
  public String toString() {
    return Base64.getUrlEncoder().encodeToString(
        Long.toString(getTimestamp().toInstant().toEpochMilli()).getBytes(StandardCharsets.UTF_8));
  }
}
