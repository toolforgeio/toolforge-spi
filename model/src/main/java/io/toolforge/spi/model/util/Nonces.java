package io.toolforge.spi.model.util;

import java.security.SecureRandom;

public final class Nonces {
  private Nonces() {}

  private static final SecureRandom RANDOM = new SecureRandom();

  /**
   * Generates a random base36 nonce string of the given length
   */
  public static String nonce36(int length) {
    if (length < 0)
      throw new IllegalArgumentException("length must not be negative");
    StringBuilder result = new StringBuilder(length);
    while (result.length() < length) {
      result.append(base36());
    }
    return result.toString();
  }

  private static char base36() {
    char result;

    int n = RANDOM.nextInt(36);
    if (n >= 0 && n < 10) {
      result = (char) ('0' + (n - 0));
    } else if (n >= 10 && n < 36) {
      result = (char) ('a' + (n - 10));
    } else {
      throw new AssertionError(n);
    }

    return result;
  }

  /**
   * Generates a random base62 nonce string of the given length
   */
  public static String nonce62(int length) {
    if (length < 0)
      throw new IllegalArgumentException("length must not be negative");
    StringBuilder result = new StringBuilder(length);
    while (result.length() < length) {
      result.append(base62());
    }
    return result.toString();
  }

  private static char base62() {
    char result;

    int n = RANDOM.nextInt(62);
    if (n >= 0 && n < 10) {
      result = (char) ('0' + (n - 0));
    } else if (n >= 10 && n < 36) {
      result = (char) ('a' + (n - 10));
    } else if (n >= 36 && n < 62) {
      result = (char) ('A' + (n - 36));
    } else {
      throw new AssertionError(n);
    }

    return result;
  }
}
