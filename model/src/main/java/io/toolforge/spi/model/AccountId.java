/*-
 * =================================LICENSE_START==================================
 * model
 * ====================================SECTION=====================================
 * Copyright (C) 2022 ToolForge
 * ====================================SECTION=====================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==================================LICENSE_END===================================
 */
package io.toolforge.spi.model;

import static java.util.Objects.requireNonNull;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class AccountId implements Comparable<AccountId> {
  public static AccountId generate() {
    return of(UUID.randomUUID());
  }

  public static AccountId of(UUID id) {
    return new AccountId(id);
  }

  public static AccountId of(String s) {
    return of(UUID.fromString(s));
  }

  @JsonCreator
  public static AccountId fromString(String s) {
    return of(s);
  }

  private final UUID uuid;

  public AccountId(UUID uuid) {
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
    AccountId other = (AccountId) obj;
    return Objects.equals(uuid, other.uuid);
  }

  @Override
  @JsonValue
  public String toString() {
    return getUuid().toString();
  }

  public static final Comparator<AccountId> COMPARATOR = Comparator.comparing(AccountId::toString);

  @Override
  public int compareTo(AccountId that) {
    return COMPARATOR.compare(this, that);
  }
}
