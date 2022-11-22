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

public class ApiKeyHint {
  private static final int LENGTH = 4;

  /**
   * matches a lowercase SHA256 hash
   */
  private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]{" + LENGTH + "}$");

  public static ApiKeyHint fromToken(ApiKeyToken token) {
    return of(token.getBody().substring(0, LENGTH));

  }

  @JsonCreator
  public static ApiKeyHint fromString(String s) {
    if (!s.startsWith(ApiKeyToken.PREFIX))
      throw new IllegalArgumentException("invalid api key hint");
    String text = s.substring(ApiKeyToken.PREFIX.length(), s.length());
    return of(text);
  }

  public static ApiKeyHint of(String text) {
    return new ApiKeyHint(text);
  }

  private final String text;

  public ApiKeyHint(String id) {
    if (!PATTERN.matcher(id).matches())
      throw new IllegalArgumentException("invalid api key hint");
    this.text = id;
  }

  /**
   * @return the id
   */
  public String getText() {
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
    ApiKeyHint other = (ApiKeyHint) obj;
    return Objects.equals(text, other.text);
  }

  @Override
  @JsonValue
  public String toString() {
    return ApiKeyToken.PREFIX + getText();
  }
}
