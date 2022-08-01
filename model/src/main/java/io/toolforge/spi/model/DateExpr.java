package io.toolforge.spi.model;

import java.time.LocalDate;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.toolforge.spi.model.expr.date.AbsoluteDateExpr;
import io.toolforge.spi.model.expr.date.RelativeDateExpr;
import io.toolforge.spi.model.expr.date.TodayDateExpr;

public abstract class DateExpr {
  @JsonCreator
  public static DateExpr fromString(String s) {
    DateExpr result;
    if (s.isEmpty()) {
      throw new IllegalArgumentException("empty");
    } else if (s.startsWith("-") || s.startsWith("+")) {
      result = RelativeDateExpr.fromString(s);
    } else if (s.equals(TodayDateExpr.TODAY)) {
      result = TodayDateExpr.fromString(s);
    } else if (Character.isDigit(s.charAt(0))) {
      result = AbsoluteDateExpr.fromString(s);
    } else {
      throw new IllegalArgumentException("invalid date expression");
    }
    return result;
  }

  public static enum Type {
    ABSOLUTE, RELATIVE, TODAY;
  }

  private final Type type;

  public DateExpr(Type type) {
    this.type = type;
  }

  /**
   * @return the type
   */
  public Type getType() {
    return type;
  }

  public abstract LocalDate eval(LocalDate today);

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DateExpr other = (DateExpr) obj;
    return type == other.type;
  }

  @Override
  @JsonValue
  public abstract String toString();
}
