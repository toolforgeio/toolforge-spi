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

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.toolforge.spi.model.util.Hexadecimal;

public class ToolVersionId {
  public static final int LENGTH = 32;

  public static ToolVersionId of(byte[] sha256) {
    return new ToolVersionId(sha256);
  }

  public static ToolVersionId fromContainerVersionId(ContainerVersionId id) {
    return fromString(id.toString());
  }

  @JsonCreator
  public static ToolVersionId fromString(String s) {
    return of(Hexadecimal.parseHex(s));
  }

  private final byte[] sha256;

  public ToolVersionId(byte[] sha256) {
    if (sha256 == null)
      throw new NullPointerException();
    if (sha256.length != LENGTH)
      throw new IllegalArgumentException("length mismatch");
    this.sha256 = sha256;
  }

  /**
   * @return the sha256
   */
  private byte[] getSha256() {
    return sha256;
  }

  public ContainerVersionId toContainerVersionId() {
    return ContainerVersionId.fromString(toString());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(sha256);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ToolVersionId other = (ToolVersionId) obj;
    return Arrays.equals(sha256, other.sha256);
  }

  @Override
  @JsonValue
  public String toString() {
    return Hexadecimal.formatHex(getSha256());
  }
}
