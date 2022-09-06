package io.toolforge.spi.model;

import static java.util.Objects.requireNonNull;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class OrganizationId implements Comparable<OrganizationId> {
  public static OrganizationId generate() {
    return of(UUID.randomUUID());
  }

  public static OrganizationId of(UUID id) {
    return new OrganizationId(id);
  }

  public static OrganizationId of(String s) {
    return of(UUID.fromString(s));
  }

  @JsonCreator
  public static OrganizationId fromString(String s) {
    return of(s);
  }

  public static OrganizationId fromAccountId(AccountId accountId) {
    return of(accountId.getUuid());
  }

  private final UUID uuid;

  public OrganizationId(UUID uuid) {
    this.uuid = requireNonNull(uuid);
  }

  /**
   * @return the text
   */
  /* default */ UUID getUuid() {
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

  public static final Comparator<OrganizationId> COMPARATOR =
      Comparator.comparing(OrganizationId::toString);

  @Override
  public int compareTo(OrganizationId that) {
    return COMPARATOR.compare(this, that);
  }
}
