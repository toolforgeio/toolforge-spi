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
package io.toolforge.spi.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Username implements Comparable<Username> {
  private static final int MAX_LENGTH = 39;

  public static class TooLongException extends IllegalArgumentException {
    private static final long serialVersionUID = 7831959129690740413L;

    public TooLongException() {
      super("Usernames can have at most " + MAX_LENGTH + " characters.");
    }
  }

  private static final Pattern INVALID_CHARACTER_PATTERN =
      Pattern.compile("[^-a-z0-9]", Pattern.CASE_INSENSITIVE);

  public static class InvalidCharacterException extends IllegalArgumentException {
    private static final long serialVersionUID = 8136136298441061002L;

    public InvalidCharacterException() {
      super("Usernames may contain only alphanumeric and `-' characters.");
    }
  }

  private static final Pattern USERNAME_PATTERN = Pattern.compile(
      "^[a-z](?:[a-z0-9]|-(?=[a-z0-9])){0," + (MAX_LENGTH - 1) + "}$", Pattern.CASE_INSENSITIVE);

  public static class InvalidUsernameException extends IllegalArgumentException {
    private static final long serialVersionUID = 2566863250255977559L;

    public InvalidUsernameException() {
      super("Usernames may not start with, end with, or contain consecutive `-' characters.");
    }
  }

  public static Username of(String text) {
    return new Username(text);
  }

  @JsonCreator
  public static Username fromString(String text) {
    return of(text);
  }

  private final String text;

  public Username(String text) {
    if (text == null)
      throw new NullPointerException();
    if (text.length() > MAX_LENGTH)
      throw new TooLongException();
    if (INVALID_CHARACTER_PATTERN.matcher(text).find())
      throw new InvalidCharacterException();
    if (!USERNAME_PATTERN.matcher(text).matches())
      throw new InvalidUsernameException();
    this.text = text;
  }

  private String getText() {
    return text;
  }

  @Override
  public int hashCode() {
    return Objects.hash(standardized());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Username other = (Username) obj;
    return Objects.equals(standardized(), other.standardized());
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }

  public static final Comparator<Username> COMPARATOR =
      Comparator.comparing(Username::standardized);

  @Override
  public int compareTo(Username that) {
    return COMPARATOR.compare(this, that);
  }

  /**
   * We treat usernames case-insensitively. This is the "standardized" representation for hashing,
   * equality, comparison, etc. However, we retain case for the user.
   */
  private String standardized() {
    return getText().toLowerCase();
  }
}
