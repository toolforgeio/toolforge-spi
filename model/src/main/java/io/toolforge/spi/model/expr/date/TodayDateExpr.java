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
import io.toolforge.spi.model.DateExpr;

public class TodayDateExpr extends DateExpr {
  public static TodayDateExpr INSTANCE = new TodayDateExpr();

  public static final String TODAY = "today";

  public static TodayDateExpr fromString(String s) {
    if (!s.equals(TODAY))
      throw new IllegalArgumentException("invalid today date expression");
    return of();
  }

  public static TodayDateExpr of() {
    return INSTANCE;
  }

  public TodayDateExpr() {
    super(Type.TODAY);
  }

  @Override
  public LocalDate eval(LocalDate today) {
    return today;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    return true;
  }

  @Override
  public String toString() {
    return TODAY;
  }
}
