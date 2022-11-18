package io.toolforge.spi.model;

import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.toolforge.spi.model.util.Nonces;

public class ApiKeyToken {
  private static final int LENGTH = 36;

  private static final String PREFIX = "tfk_";

  private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]{" + LENGTH + "}$");

  public static ApiKeyToken generate() {
    return of(new StringBuilder().append(PREFIX).append(Nonces.nonce62(LENGTH)).toString());

  }

  /**
   * @throws IllegalArgumentException if s does not contain a valid API key
   * @throws NullPointerException if s is null
   */
  @JsonCreator
  public static ApiKeyToken fromString(String s) {
    if (!s.startsWith(PREFIX))
      throw new IllegalArgumentException("invalid api key");
    String id = s.substring(PREFIX.length(), s.length());
    return of(id);
  }

  /**
   * @throws IllegalArgumentException if s does not contain a valid API key ID
   * @throws NullPointerException if s is null
   */
  public static ApiKeyToken of(String id) {
    return new ApiKeyToken(id);
  }

  private final String body;

  /**
   * @throws IllegalArgumentException if text does not contain a valid API key
   * @throws NullPointerException if text is null
   */
  public ApiKeyToken(String body) {
    if (!PATTERN.matcher(body).matches())
      throw new IllegalArgumentException("invalid api key body");
    this.body = body;
  }

  /**
   * @return the text
   */
  public String getBody() {
    return body;
  }

  @Override
  public int hashCode() {
    return Objects.hash(body);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ApiKeyToken other = (ApiKeyToken) obj;
    return Objects.equals(body, other.body);
  }

  @Override
  @JsonValue
  public String toString() {
    return PREFIX + getBody();
  }
}
