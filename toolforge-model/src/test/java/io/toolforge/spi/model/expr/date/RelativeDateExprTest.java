package io.toolforge.spi.model.expr.date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDate;
import org.junit.Test;

public class RelativeDateExprTest {
  @Test
  public void evalYearUnitTest() {
    final LocalDate today = LocalDate.of(2020, 3, 8);
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.YEAR).eval(today),
        is(LocalDate.of(2019, 3, 8)));
  }

  @Test
  public void evalMonthUnitTest() {
    final LocalDate today = LocalDate.of(2020, 3, 8);
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.MONTH).eval(today),
        is(LocalDate.of(2020, 2, 8)));
  }

  @Test
  public void evalWeekUnitTest() {
    final LocalDate today = LocalDate.of(2020, 3, 8);
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.WEEK).eval(today),
        is(LocalDate.of(2020, 3, 1)));
  }

  @Test
  public void evalDayUnitTest() {
    final LocalDate today = LocalDate.of(2020, 3, 8);
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.DAY).eval(today),
        is(LocalDate.of(2020, 3, 7)));
  }

  @Test
  public void evalNegativeAmountTest() {
    final LocalDate today = LocalDate.of(2020, 3, 8);
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.DAY).eval(today),
        is(LocalDate.of(2020, 3, 7)));
  }

  @Test
  public void evalPositiveAmountTest() {
    final LocalDate today = LocalDate.of(2020, 3, 8);
    assertThat(RelativeDateExpr.of(+1, RelativeDateExpr.DateUnit.DAY).eval(today),
        is(LocalDate.of(2020, 3, 9)));
  }

  @Test
  public void fromStringYearUnitTest() {
    assertThat(RelativeDateExpr.fromString("-1y"),
        is(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.YEAR)));
  }

  @Test
  public void toStringYearUnitTest() {
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.YEAR).toString(), is("-1y"));
  }

  @Test
  public void fromStringMonthUnitTest() {
    assertThat(RelativeDateExpr.fromString("-1m"),
        is(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.MONTH)));
  }

  @Test
  public void toStringMonthUnitTest() {
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.MONTH).toString(), is("-1m"));
  }

  @Test
  public void fromStringWeekUnitTest() {
    assertThat(RelativeDateExpr.fromString("-1w"),
        is(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.WEEK)));
  }

  @Test
  public void toStringWeekUnitTest() {
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.WEEK).toString(), is("-1w"));
  }

  @Test
  public void fromStringDayUnitTest() {
    assertThat(RelativeDateExpr.fromString("-1d"),
        is(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.DAY)));
  }

  @Test
  public void toStringDayUnitTest() {
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.DAY).toString(), is("-1d"));
  }

  @Test
  public void fromStringNegativeAmountTest() {
    assertThat(RelativeDateExpr.fromString("-1d"),
        is(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.DAY)));
  }

  @Test
  public void toStringNegativeAmountTest() {
    assertThat(RelativeDateExpr.of(-1, RelativeDateExpr.DateUnit.DAY).toString(), is("-1d"));
  }

  @Test
  public void fromStringPositiveAmountTest() {
    assertThat(RelativeDateExpr.fromString("+1d"),
        is(RelativeDateExpr.of(+1, RelativeDateExpr.DateUnit.DAY)));
  }

  @Test
  public void toStringPositiveAmountTest() {
    assertThat(RelativeDateExpr.of(+1, RelativeDateExpr.DateUnit.DAY).toString(), is("+1d"));
  }
}
