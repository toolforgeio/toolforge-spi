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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class PrefixQuery {
  private static final Pattern PATTERN = Pattern.compile("^\\w*$");

  public static PrefixQuery EMPTY = PrefixQuery.of("");

  @JsonCreator
  public static PrefixQuery fromString(String s) {
    return of(s);
  }

  public static PrefixQuery of(String prefix) {
    return new PrefixQuery(prefix);
  }

  private final String prefix;

  public PrefixQuery(String prefix) {
    Matcher m = PATTERN.matcher(prefix);
    if (!m.matches())
      throw new IllegalArgumentException("invalid prefix query: " + prefix);
    this.prefix = prefix;
  }

  /**
   * @return the prefix
   */
  public String getPrefix() {
    return prefix;
  }

  public boolean isEmpty() {
    return getPrefix().isEmpty();
  }

  @Override
  public int hashCode() {
    return Objects.hash(prefix);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PrefixQuery other = (PrefixQuery) obj;
    return Objects.equals(prefix, other.prefix);
  }

  @Override
  @JsonValue
  public String toString() {
    return getPrefix();
  }
}
