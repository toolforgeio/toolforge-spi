package io.toolforge.spi.model;

import static java.util.Objects.requireNonNull;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class BlobId implements Comparable<BlobId> {
  public static BlobId generate() {
    return of(UUID.randomUUID());
  }

  public static BlobId of(UUID uuid) {
    return new BlobId(uuid);
  }

  @JsonCreator
  public static BlobId fromString(String s) {
    return of(UUID.fromString(s));
  }

  private final UUID uuid;

  public BlobId(UUID uuid) {
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
    BlobId other = (BlobId) obj;
    return Objects.equals(uuid, other.uuid);
  }

  @Override
  @JsonValue
  public String toString() {
    return getUuid().toString();
  }

  public static final Comparator<BlobId> COMPARATOR = Comparator.comparing(BlobId::toString);

  @Override
  public int compareTo(BlobId that) {
    return COMPARATOR.compare(this, that);
  }
}
