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

import static java.util.Objects.requireNonNull;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class LogCursor {
  @JsonCreator
  public static LogCursor fromString(String s) {
    return of(s);
  }

  public static LogCursor of(String token) {
    return new LogCursor(token);
  }

  private final String token;

  public LogCursor(String token) {
    this.token = requireNonNull(token);
  }

  /**
   * @return the token
   */
  public String getToken() {
    return token;
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    LogCursor other = (LogCursor) obj;
    return Objects.equals(token, other.token);
  }

  @Override
  @JsonValue
  public String toString() {
    return getToken();
  }
}
