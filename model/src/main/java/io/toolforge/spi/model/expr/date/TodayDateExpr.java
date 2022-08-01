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
