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
package io.toolforge.spi.model.expr.date;

import java.time.LocalDate;
import java.util.Objects;
import io.toolforge.spi.model.DateExpr;

public class AbsoluteDateExpr extends DateExpr {
  public static AbsoluteDateExpr fromString(String s) {
    return of(LocalDate.parse(s));
  }

  public static AbsoluteDateExpr of(LocalDate value) {
    return new AbsoluteDateExpr(value);
  }

  private final LocalDate value;

  public AbsoluteDateExpr(LocalDate value) {
    super(Type.ABSOLUTE);
    if (value == null)
      throw new NullPointerException();
    this.value = value;
  }

  /**
   * @return the value
   */
  public LocalDate getValue() {
    return value;
  }

  @Override
  public LocalDate eval(LocalDate today) {
    return getValue();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(value);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbsoluteDateExpr other = (AbsoluteDateExpr) obj;
    return Objects.equals(value, other.value);
  }

  @Override
  public String toString() {
    return getValue().toString();
  }
}
