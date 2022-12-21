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

public class EmailAddress implements Comparable<EmailAddress> {
  private static final int MAX_LENGTH = 320;

  public static class TooLongException extends IllegalArgumentException {
    private static final long serialVersionUID = 7831959129690740413L;

    public TooLongException() {
      super("Email addresses can have at most " + MAX_LENGTH + " characters.");
    }
  }

  private static final Pattern PATTERN = Pattern.compile(
      "^[-a-zA-Z0-9_]+(?:[.][-a-zA-Z0-9_]+)*(?:[+][-a-zA-Z0-9_]+)?@(?:[a-zA-Z0-9-]+[.])+[a-zA-Z]{2,7}$");

  // TODO We probably need better error messages...
  public static class InvalidEmailAddressException extends IllegalArgumentException {
    private static final long serialVersionUID = 2566863250255977559L;

    public InvalidEmailAddressException() {
      super("The email address is not valid.");
    }
  }

  public static EmailAddress of(String text) {
    return new EmailAddress(text);
  }

  @JsonCreator
  public static EmailAddress fromString(String text) {
    return of(text);
  }

  private final String text;

  public EmailAddress(String text) {
    if (text == null)
      throw new NullPointerException();
    if (text.length() > MAX_LENGTH)
      throw new TooLongException();
    if (!PATTERN.matcher(text).matches())
      throw new InvalidEmailAddressException();
    this.text = text.toLowerCase();
  }

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
    EmailAddress other = (EmailAddress) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }

  public static final Comparator<EmailAddress> COMPARATOR =
      Comparator.comparing(EmailAddress::toString);

  @Override
  public int compareTo(EmailAddress that) {
    return COMPARATOR.compare(this, that);
  }
}
