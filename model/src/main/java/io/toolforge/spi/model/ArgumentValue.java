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

import java.math.BigDecimal;
import java.util.Objects;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.toolforge.spi.model.serialization.ArgumentValueDeserializer;
import io.toolforge.spi.model.serialization.ArgumentValueSerializer;

@JsonSerialize(using = ArgumentValueSerializer.class)
@JsonDeserialize(using = ArgumentValueDeserializer.class)
public class ArgumentValue {
  public static final ArgumentValue NULL = new ArgumentValue((Object) null);

  public static final ArgumentValue TRUE = new ArgumentValue(Boolean.TRUE);

  public static final ArgumentValue FALSE = new ArgumentValue(Boolean.TRUE);

  private final Object value;

  public ArgumentValue(BigDecimal value) {
    this((Object) value);
  }

  public ArgumentValue(String value) {
    this((Object) value);
  }

  public ArgumentValue(Boolean value) {
    this((Object) value);
  }

  public ArgumentValue(Object value) {
    if (value != null
        && !(value instanceof BigDecimal || value instanceof String || value instanceof Boolean))
      throw new IllegalArgumentException("unrecognized value: " + value);
    this.value = value;
  }

  public Object getValue() {
    return value;
  }

  public boolean isNull() {
    return getValue() == null;
  }

  public boolean isNumber() {
    return getValue() instanceof BigDecimal;
  }

  public BigDecimal numberVal() {
    return (BigDecimal) getValue();
  }

  public boolean isString() {
    return getValue() instanceof String;
  }

  public String stringVal() {
    return (String) getValue();
  }

  public boolean isBoolean() {
    return getValue() instanceof Boolean;
  }

  public Boolean booleanVal() {
    return (Boolean) getValue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ArgumentValue other = (ArgumentValue) obj;
    return Objects.equals(value, other.value);
  }

  @Override
  public String toString() {
    return Objects.toString(getValue());
  }
}
