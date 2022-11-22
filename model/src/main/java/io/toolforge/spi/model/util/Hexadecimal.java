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

public final class Hexadecimal {
  private Hexadecimal() {}

  private static final char[] HEX_ARRAY =
      new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  /**
   * Converts the given byte array into a lowercase hexadecimal string.
   * 
   * @see <a href=
   *      "https://stackoverflow.com/a/9855338/2103602">https://stackoverflow.com/a/9855338/2103602</a>
   */
  public static String formatHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars);
  }

  public static byte[] parseHex(CharSequence s) {
    if (s.length() % 2 == 1)
      throw new IllegalArgumentException("hex string must have even length");
    byte[] result = new byte[s.length() / 2];
    for (int i = 0; i < s.length(); i = i + 2) {
      int first = parseHexDigit(s.charAt(i + 0));
      int second = parseHexDigit(s.charAt(i + 1));
      result[i / 2] = (byte) ((first << 4 | second) & 0xFF);
    }
    return result;
  }

  private static int parseHexDigit(char ch) {
    int result;
    if (ch >= '0' && ch <= '9') {
      result = 0 + (ch - '0');
    } else if (ch >= 'a' && ch <= 'f') {
      result = 10 + (ch - 'a');
    } else if (ch >= 'A' && ch <= 'F') {
      result = 10 + (ch - 'A');
    } else {
      throw new IllegalArgumentException("invalid hex character " + ch);
    }
    return result;
  }
}
