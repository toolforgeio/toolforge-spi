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
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import io.toolforge.spi.model.DateExpr;

public class RelativeDateExpr extends DateExpr {
  public static enum DateUnit {
    DAY("d", ChronoUnit.DAYS), WEEK("w", ChronoUnit.WEEKS), MONTH("m", ChronoUnit.MONTHS), YEAR("y",
        ChronoUnit.YEARS);

    public static DateUnit fromAbbreviation(String s) {
      return Arrays.stream(values()).filter(v -> v.getAbbreviation().equals(s)).findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }

    private final String abbreviation;
    private final TemporalUnit temporalUnit;

    private DateUnit(String abbreviation, TemporalUnit temporalUnit) {
      this.abbreviation = abbreviation;
      this.temporalUnit = temporalUnit;
    }

    /**
     * @return the abbreviation
     */
    public String getAbbreviation() {
      return abbreviation;
    }

    /**
     * @return the temporalUnit
     */
    public TemporalUnit getTemporalUnit() {
      return temporalUnit;
    }
  }

  public static RelativeDateExpr of(int amount, DateUnit unit) {
    return new RelativeDateExpr(amount, unit);
  }

  private static final Pattern PATTERN = Pattern.compile("^([-+][1-9][0-9]*)([dwmy])$");

  public static RelativeDateExpr fromString(String s) {
    Matcher m = PATTERN.matcher(s);
    if (!m.matches())
      throw new IllegalArgumentException("invalid relative date expression");

    int amount = Integer.parseInt(m.group(1));
    DateUnit unit = DateUnit.fromAbbreviation(m.group(2));

    return of(amount, unit);
  }

  private final int amount;
  private final DateUnit unit;

  public RelativeDateExpr(int amount, DateUnit unit) {
    super(Type.RELATIVE);
    if (unit == null)
      throw new NullPointerException();
    this.amount = amount;
    this.unit = unit;
  }

  @Override
  public LocalDate eval(LocalDate today) {
    return today.plus(getAmount(), getUnit().getTemporalUnit());
  }

  /**
   * @return the amount
   */
  public int getAmount() {
    return amount;
  }

  /**
   * @return the unit
   */
  public DateUnit getUnit() {
    return unit;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(amount, unit);
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
    RelativeDateExpr other = (RelativeDateExpr) obj;
    return amount == other.amount && unit == other.unit;
  }


  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    if (getAmount() >= 0) {
      result.append("+").append(getAmount());
    } else {
      result.append(getAmount());
    }
    return result.append(getUnit().getAbbreviation()).toString();
  }
}
