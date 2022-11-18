package io.toolforge.spi.model;

import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ApiKeyHint {
  private static final int LENGTH = 4;

  /**
   * matches a lowercase SHA256 hash
   */
  private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]{" + LENGTH + "}$");

  public static ApiKeyHint fromToken(ApiKeyToken token) {
    return of(token.getBody().substring(0, LENGTH));

  }

  @JsonCreator
  public static ApiKeyHint fromString(String text) {
    return of(text);
  }

  public static ApiKeyHint of(String text) {
    return new ApiKeyHint(text);
  }

  private final String text;

  public ApiKeyHint(String id) {
    if (!PATTERN.matcher(id).matches())
      throw new IllegalArgumentException("invalid api key hint");
    this.text = id;
  }

  /**
   * @return the id
   */
  public String getText() {
    return text;
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ApiKeyHint other = (ApiKeyHint) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }
}
