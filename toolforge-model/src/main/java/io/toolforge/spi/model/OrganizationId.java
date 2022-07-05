package io.toolforge.spi.model;

import static java.util.Objects.requireNonNull;
import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class OrganizationId {
  public static OrganizationId generate() {
    return of(UUID.randomUUID());
  }

  public static OrganizationId of(UUID uuid) {
    return new OrganizationId(uuid);
  }

  @JsonCreator
  public static OrganizationId fromString(String s) {
    return of(UUID.fromString(s));
  }

  private final UUID uuid;

  public OrganizationId(UUID uuid) {
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
    OrganizationId other = (OrganizationId) obj;
    return Objects.equals(uuid, other.uuid);
  }

  @Override
  @JsonValue
  public String toString() {
    return getUuid().toString();
  }
}
