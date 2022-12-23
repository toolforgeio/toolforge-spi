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

import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class AccountReference {
  public static final String ME = "me";

  @JsonCreator
  public static AccountReference fromString(String s) {
    return of(s);
  }

  public static AccountReference of(String text) {
    return new AccountReference(text);
  }

  private static final Pattern PATTERN =
      Pattern.compile("^(?:[a-z0-9]{"+AccountId.LENGTH+"}|me)$");

  private final String text;

  public AccountReference(String text) {
    if (!PATTERN.matcher(text).matches())
      throw new IllegalArgumentException("invalid account reference: " + text);
    this.text = text;
  }

  public AccountId resolve(AccountId me) {
    return getText().equals(ME) ? me : AccountId.fromString(getText());
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
    AccountReference other = (AccountReference) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }
}
