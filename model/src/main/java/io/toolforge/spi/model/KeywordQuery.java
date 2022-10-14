package io.toolforge.spi.model;

import static java.util.Collections.unmodifiableList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class KeywordQuery {
  public static KeywordQuery EMPTY = KeywordQuery.of(List.of());

  private static final Pattern TOKEN_PATTERN = Pattern.compile("\\w{3,}");

  @JsonCreator
  public static KeywordQuery fromString(String s) {
    return of(TOKEN_PATTERN.matcher(s).results().map(r -> r.group()).toList());
  }

  public static KeywordQuery of(List<String> tokens) {
    return new KeywordQuery(tokens);
  }

  private final List<String> tokens;

  public KeywordQuery(List<String> tokens) {
    this.tokens = unmodifiableList(tokens);
  }

  /**
   * @return the tokens
   */
  public List<String> getTokens() {
    return tokens;
  }

  public boolean isEmpty() {
    return getTokens().isEmpty();
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokens);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    KeywordQuery other = (KeywordQuery) obj;
    return Objects.equals(tokens, other.tokens);
  }

  @Override
  @JsonValue
  public String toString() {
    return String.join(" ", getTokens());
  }
}
