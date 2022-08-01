package io.toolforge.spi.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.toolforge.spi.model.util.Nonces;

public class BlobId implements Comparable<BlobId> {
  private static final Pattern PATTERN = Pattern.compile("^[0-9a-z]{8}$");

  public static BlobId generate() {
    return of(Nonces.nonce36(8));
  }

  public static BlobId of(String s) {
    return new BlobId(s);
  }

  @JsonCreator
  public static BlobId fromString(String s) {
    return of(s);
  }

  private final String text;

  public BlobId(String text) {
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
    BlobId other = (BlobId) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }

  public static final Comparator<BlobId> COMPARATOR = Comparator.comparing(BlobId::toString);

  @Override
  public int compareTo(BlobId that) {
    return COMPARATOR.compare(this, that);
  }
}
