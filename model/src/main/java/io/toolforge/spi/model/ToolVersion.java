package io.toolforge.spi.model;

import java.util.Arrays;
import java.util.HexFormat;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ToolVersion {
  public static final int LENGTH = 32;

  public static ToolVersion of(byte[] sha256) {
    return new ToolVersion(sha256);
  }

  @JsonCreator
  public static ToolVersion fromString(String s) {
    return of(HexFormat.of().parseHex(s));
  }

  private final byte[] sha256;

  public ToolVersion(byte[] sha256) {
    if (sha256 == null)
      throw new NullPointerException();
    if (sha256.length != LENGTH)
      throw new IllegalArgumentException("length mismatch");
    this.sha256 = sha256;
  }

  /**
   * @return the sha256
   */
  private byte[] getSha256() {
    return sha256;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(sha256);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ToolVersion other = (ToolVersion) obj;
    return Arrays.equals(sha256, other.sha256);
  }

  @Override
  @JsonValue
  public String toString() {
    return HexFormat.of().formatHex(getSha256());
  }
}
