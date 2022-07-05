package io.toolforge.spi.model;

import static java.util.Objects.requireNonNull;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class ToolId implements Comparable<ToolId> {
  public static ToolId generate() {
    return of(UUID.randomUUID());
  }

  public static ToolId of(UUID uuid) {
    return new ToolId(uuid);
  }

  @JsonCreator
  public static ToolId fromString(String s) {
    return of(UUID.fromString(s));
  }

  private final UUID uuid;

  public ToolId(UUID uuid) {
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
    ToolId other = (ToolId) obj;
    return Objects.equals(uuid, other.uuid);
  }

  @Override
  @JsonValue
  public String toString() {
    return getUuid().toString();
  }

  public static final Comparator<ToolId> COMPARATOR = Comparator.comparing(ToolId::toString);

  @Override
  public int compareTo(ToolId that) {
    return COMPARATOR.compare(this, that);
  }
}
