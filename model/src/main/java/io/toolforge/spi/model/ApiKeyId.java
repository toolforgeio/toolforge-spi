package io.toolforge.spi.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ApiKeyId {
  /**
   * matches a lowercase SHA256 hash
   */
  private static final Pattern PATTERN = Pattern.compile("^[a-f0-9]{64}$");

  private static final HexFormat HEX_FORMAT = HexFormat.of().withLowerCase();

  public static ApiKeyId fromToken(ApiKeyToken token) {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      throw new AssertionError("JDK does not implement required hashing algorithm SHA-256", e);
    }
    byte[] hashCode = md.digest(token.getBody().getBytes(StandardCharsets.UTF_8));
    String id = HEX_FORMAT.formatHex(hashCode);
    return of(id);

  }

  @JsonCreator
  public static ApiKeyId fromString(String text) {
    return of(text);
  }

  public static ApiKeyId of(String text) {
    return new ApiKeyId(text);
  }

  private final String text;

  public ApiKeyId(String id) {
    if (!PATTERN.matcher(id).matches())
      throw new IllegalArgumentException("invalid api key id");
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
    ApiKeyId other = (ApiKeyId) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }
}
