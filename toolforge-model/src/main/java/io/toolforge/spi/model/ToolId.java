package io.toolforge.spi.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.toolforge.spi.model.util.Nonces;

public class ToolId implements Comparable<ToolId> {
  private static final Pattern PATTERN = Pattern.compile("^[0-9a-z]{8}$");

  public static ToolId generate() {
    return of(Nonces.nonce36(8));
  }

  public static ToolId of(String s) {
    return new ToolId(s);
  }

  @JsonCreator
  public static ToolId fromString(String s) {
    return of(s);
  }

  private final String text;

  public ToolId(String text) {
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
    ToolId other = (ToolId) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }

  public static final Comparator<ToolId> COMPARATOR = Comparator.comparing(ToolId::toString);

  @Override
  public int compareTo(ToolId that) {
    return COMPARATOR.compare(this, that);
  }
}
