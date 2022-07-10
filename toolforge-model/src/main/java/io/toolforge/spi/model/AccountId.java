package io.toolforge.spi.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.toolforge.spi.model.util.Nonces;

public class AccountId implements Comparable<AccountId> {
  private static final Pattern PATTERN = Pattern.compile("^[0-9a-z]{8}$");

  public static AccountId generate() {
    return of(Nonces.nonce36(8));
  }

  public static AccountId of(String s) {
    return new AccountId(s);
  }

  @JsonCreator
  public static AccountId fromString(String s) {
    return of(s);
  }

  private final String text;

  public AccountId(String text) {
    if (!PATTERN.matcher(text).matches())
      throw new IllegalArgumentException(text);
    this.text = text;
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
    AccountId other = (AccountId) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }

  public static final Comparator<AccountId> COMPARATOR = Comparator.comparing(AccountId::toString);

  @Override
  public int compareTo(AccountId that) {
    return COMPARATOR.compare(this, that);
  }
}
