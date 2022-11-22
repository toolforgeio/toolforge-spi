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
import io.toolforge.spi.model.util.Nonces;

public class ExecutionId implements Comparable<ExecutionId> {
  private static final Pattern PATTERN = Pattern.compile("^[0-9a-z]{8}$");

  public static ExecutionId generate() {
    return of(Nonces.nonce36(8));
  }

  public static ExecutionId of(String s) {
    return new ExecutionId(s);
  }

  @JsonCreator
  public static ExecutionId fromString(String s) {
    return of(s);
  }

  private final String text;

  public ExecutionId(String text) {
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
    ExecutionId other = (ExecutionId) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return getText();
  }

  public static final Comparator<ExecutionId> COMPARATOR =
      Comparator.comparing(ExecutionId::toString);

  @Override
  public int compareTo(ExecutionId that) {
    return COMPARATOR.compare(this, that);
  }
}
