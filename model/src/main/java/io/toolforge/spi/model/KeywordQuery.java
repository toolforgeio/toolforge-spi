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

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class KeywordQuery {
  public static KeywordQuery EMPTY = KeywordQuery.of(emptyList());

  private static final Pattern TOKEN_PATTERN = Pattern.compile("\\w{3,}");

  @JsonCreator
  public static KeywordQuery fromString(String s) {
    List<String> tokens = new ArrayList<>();

    Matcher m = TOKEN_PATTERN.matcher(s);
    while (m.find())
      tokens.add(m.group());

    return KeywordQuery.of(unmodifiableList(tokens));
  }

  public static KeywordQuery of(List<String> tokens) {
    return new KeywordQuery(tokens);
  }

  private final List<String> tokens;

  public KeywordQuery(List<String> tokens) {
    if (!tokens.stream().allMatch(s -> TOKEN_PATTERN.matcher(s).matches()))
      throw new IllegalArgumentException("invalid tokens: " + tokens);
    this.tokens = unmodifiableList(tokens);
  }

  /**
   * @return the tokens
   */
  public List<String> getTokens() {
    return tokens;
  }

  public boolean isEmpty() {
    return getTokens().isEmpty();
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokens);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    KeywordQuery other = (KeywordQuery) obj;
    return Objects.equals(tokens, other.tokens);
  }

  @Override
  @JsonValue
  public String toString() {
    return String.join(" ", getTokens());
  }
}
