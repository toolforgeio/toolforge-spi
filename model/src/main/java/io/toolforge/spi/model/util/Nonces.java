/*-
 * =================================LICENSE_START==================================
 * model
 * ====================================SECTION=====================================
 * Copyright (C) 2022 ToolForge
 * ====================================SECTION=====================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==================================LICENSE_END===================================
 */
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
