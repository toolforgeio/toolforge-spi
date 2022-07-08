package io.toolforge.spi.model;

import static java.util.Objects.requireNonNull;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ExecutionId implements Comparable<ExecutionId> {
  public static ExecutionId generate() {
    return of(UUID.randomUUID());
  }

  public static ExecutionId of(UUID uuid) {
    return new ExecutionId(uuid);
  }

  @JsonCreator
  public static ExecutionId fromString(String s) {
    return of(UUID.fromString(s));
  }

  private final UUID uuid;

  public ExecutionId(UUID uuid) {
    this.uuid = requireNonNull(uuid);
  }

  private UUID getUuid() {
    return uuid;
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ExecutionId other = (ExecutionId) obj;
    return Objects.equals(uuid, other.uuid);
  }

  @Override
  @JsonValue
  public String toString() {
    return getUuid().toString();
  }

  public static final Comparator<ExecutionId> COMPARATOR = Comparator.comparing(ExecutionId::toString);

  @Override
  public int compareTo(ExecutionId that) {
    return COMPARATOR.compare(this, that);
  }
}
