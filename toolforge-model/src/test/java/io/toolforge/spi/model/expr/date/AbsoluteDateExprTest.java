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
