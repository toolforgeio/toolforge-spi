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
import io.toolforge.spi.model.util.Nonces;

public class ApiKeyToken {
  private static final int LENGTH = 36;

  public static final String PREFIX = "tfk_";

  private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]{" + LENGTH + "}$");

  public static ApiKeyToken generate() {
    return of(Nonces.nonce62(LENGTH));

  }

  /**
   * @throws IllegalArgumentException if s does not contain a valid API key
   * @throws NullPointerException if s is null
   */
  @JsonCreator
  public static ApiKeyToken fromString(String s) {
    if (!s.startsWith(PREFIX))
      throw new IllegalArgumentException("invalid api key");
    String id = s.substring(PREFIX.length(), s.length());
    return of(id);
  }

  /**
   * @throws IllegalArgumentException if s does not contain a valid API key ID
   * @throws NullPointerException if s is null
   */
  public static ApiKeyToken of(String id) {
    return new ApiKeyToken(id);
  }

  private final String body;

  /**
   * @throws IllegalArgumentException if text does not contain a valid API key
   * @throws NullPointerException if text is null
   */
  public ApiKeyToken(String body) {
    if (!PATTERN.matcher(body).matches())
      throw new IllegalArgumentException("invalid api key body");
    this.body = body;
  }

  /**
   * @return the text
   */
  public String getBody() {
    return body;
  }

  @Override
  public int hashCode() {
    return Objects.hash(body);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ApiKeyToken other = (ApiKeyToken) obj;
    return Objects.equals(body, other.body);
  }

  @Override
  @JsonValue
  public String toString() {
    return PREFIX + getBody();
  }

  public static void main(String[] args) {
    ApiKeyToken token = ApiKeyToken.generate();
    ApiKeyId id = ApiKeyId.fromToken(token);
    ApiKeyHint hint = ApiKeyHint.fromToken(token);
    System.out.println("Token: " + token);
    System.out.println("ID:    " + id);
    System.out.println("Hint:  " + hint);
  }
}
