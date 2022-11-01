package io.toolforge.spi.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class PrefixQuery {
  public static PrefixQuery EMPTY = PrefixQuery.of("");

  private static final Pattern PATTERN = Pattern.compile("^\\w*$");

  @JsonCreator
  public static PrefixQuery fromString(String s) {
    return of(s);
  }

  public static PrefixQuery of(String prefix) {
    return new PrefixQuery(prefix);
  }

  private final String prefix;

  public PrefixQuery(String prefix) {
    Matcher m = PATTERN.matcher(prefix);
    if (!m.matches())
      throw new IllegalArgumentException("invalid prefix query: " + prefix);
    this.prefix = prefix;
  }

  /**
   * @return the prefix
   */
  public String getPrefix() {
    return prefix;
  }

  public boolean isEmpty() {
    return getPrefix().isEmpty();
  }

  @Override
  public int hashCode() {
    return Objects.hash(prefix);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PrefixQuery other = (PrefixQuery) obj;
    return Objects.equals(prefix, other.prefix);
  }

  @Override
  @JsonValue
  public String toString() {
    return getPrefix();
  }
}
