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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDate;
import org.junit.Test;

public class AbsoluteDateExprTest {
  @Test
  public void evalTest() {
    final LocalDate today = LocalDate.now();
    final LocalDate then = LocalDate.of(2020, 3, 8);
    assertThat(AbsoluteDateExpr.of(then).eval(today), is(then));
  }

  @Test
  public void fromStringTest() {
    final LocalDate then = LocalDate.of(2020, 3, 8);
    assertThat(AbsoluteDateExpr.fromString("2020-03-08"), is(AbsoluteDateExpr.of(then)));
  }

  @Test
  public void toStringTest() {
    final LocalDate then = LocalDate.of(2020, 3, 8);
    assertThat(AbsoluteDateExpr.of(then).toString(), is("2020-03-08"));
  }
}
