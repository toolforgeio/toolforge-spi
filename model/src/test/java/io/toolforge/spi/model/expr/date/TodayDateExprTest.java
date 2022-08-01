package io.toolforge.spi.model.expr.date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDate;
import org.junit.Test;

public class TodayDateExprTest {
  @Test
  public void evalTest() {
    final LocalDate today = LocalDate.now();
    assertThat(TodayDateExpr.of().eval(today), is(today));
  }

  @Test
  public void fromStringTest() {
    assertThat(TodayDateExpr.fromString("today"), is(TodayDateExpr.of()));
  }

  @Test
  public void toStringTest() {
    assertThat(TodayDateExpr.of().toString(), is("today"));
  }
}
