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

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ContainerCursor {
  @JsonCreator
  public static ContainerCursor fromString(String s) {
    return of(
        Integer.parseInt(new String(Base64.getUrlDecoder().decode(s), StandardCharsets.UTF_8)));
  }

  public static ContainerCursor of(int offset) {
    return new ContainerCursor(offset);
  }

  private final int offset;

  public ContainerCursor(int offset) {
    if (offset < 0)
      throw new IllegalArgumentException("offset must not be negative");
    this.offset = offset;
  }

  /**
   * @return the offset
   */
  public int getOffset() {
    return offset;
  }

  @Override
  public int hashCode() {
    return Objects.hash(offset);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ContainerCursor other = (ContainerCursor) obj;
    return offset == other.offset;
  }

  @Override
  @JsonValue
  public String toString() {
    return Base64.getUrlEncoder()
        .encodeToString(Integer.toString(getOffset()).getBytes(StandardCharsets.UTF_8));
  }
}
