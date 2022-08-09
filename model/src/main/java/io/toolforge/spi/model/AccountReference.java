package io.toolforge.spi.model;

import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class AccountReference {
  public static final String ME = "me";

  @JsonCreator
  public static AccountReference fromString(String s) {
    return of(s);
  }

  public static AccountReference of(String text) {
    return new AccountReference(text);
  }

  private static final Pattern PATTERN =
      Pattern.compile("^(?:[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}|me)$");

  private final String text;

  public AccountReference(String text) {
    if (!PATTERN.matcher(text).matches())
      throw new IllegalArgumentException("invalid account reference: " + text);
    this.text = text;
  }

  public AccountId resolve(AccountId me) {
    return getText().equals(ME) ? me : AccountId.fromString(getText());
  }

  /**
   * @return the text
   */
  private String getText() {
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
    AccountReference other = (AccountReference) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }
}
