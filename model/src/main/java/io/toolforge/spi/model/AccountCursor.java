package io.toolforge.spi.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class AccountCursor {
  @JsonCreator
  public static AccountCursor fromString(String s) {
    return of(
        Integer.parseInt(new String(Base64.getUrlDecoder().decode(s), StandardCharsets.UTF_8)));
  }

  public static AccountCursor of(int offset) {
    return new AccountCursor(offset);
  }

  private final int offset;

  public AccountCursor(int offset) {
    if (offset < 0)
      throw new IllegalArgumentException("offset must not be negative");
    this.offset = offset;
  }

  /**
   * @return the offset
   */
  public int getOffset() {
    return offset;
  }

  @Override
  public int hashCode() {
    return Objects.hash(offset);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AccountCursor other = (AccountCursor) obj;
    return offset == other.offset;
  }

  @Override
  @JsonValue
  public String toString() {
    return Base64.getUrlEncoder()
        .encodeToString(Integer.toString(getOffset()).getBytes(StandardCharsets.UTF_8));
  }
}
