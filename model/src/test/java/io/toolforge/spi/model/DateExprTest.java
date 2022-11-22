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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDate;
import org.junit.Test;
import io.toolforge.spi.model.expr.date.AbsoluteDateExpr;
import io.toolforge.spi.model.expr.date.RelativeDateExpr;
import io.toolforge.spi.model.expr.date.TodayDateExpr;

public class DateExprTest {
  @Test
  public void fromStringTodayTest() {
    assertThat(DateExpr.fromString("today"), is(TodayDateExpr.of()));
  }

  @Test
  public void fromStringAbsoluteTest() {
    assertThat(DateExpr.fromString("2020-01-01"),
        is(AbsoluteDateExpr.of(LocalDate.of(2020, 1, 1))));
  }

  @Test
  public void fromStringRelativeTest() {
    assertThat(DateExpr.fromString("-1y"),
        is(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.YEAR)));
  }
}
