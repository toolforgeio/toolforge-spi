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
