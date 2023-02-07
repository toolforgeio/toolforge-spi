/*-
 * =================================LICENSE_START==================================
 * auth
 * ====================================SECTION=====================================
 * Copyright (C) 2022 - 2023 ToolForge
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
package io.toolforge.spi.auth;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;
import java.util.Objects;
import java.util.Set;
import io.toolforge.spi.model.AccountId;
import io.toolforge.spi.model.EmailAddress;
import io.toolforge.spi.model.HumanName;
import io.toolforge.spi.model.Username;

public class UserDefinition {
  public static UserDefinition of(AccountId id, Username username, HumanName name,
      EmailAddress email, String picture, Set<String> roles) {
    return new UserDefinition(id, username, name, email, picture, roles);
  }

  private final AccountId id;
  private final Username username;
  private final HumanName name;
  private final EmailAddress email;
  private final String picture;
  private final Set<String> roles;

  public UserDefinition(AccountId id, Username username, HumanName name, EmailAddress email,
      String picture, Set<String> roles) {
    this.id = requireNonNull(id);
    this.username = requireNonNull(username);
    this.name = requireNonNull(name);
    this.email = requireNonNull(email);
    this.picture = requireNonNull(picture);
    this.roles = unmodifiableSet(roles);
  }

  /**
   * @return the id
   */
  public AccountId getId() {
    return id;
  }

  /**
   * @return the username
   */
  public Username getUsername() {
    return username;
  }

  /**
   * @return the name
   */
  public HumanName getName() {
    return name;
  }

  /**
   * @return the email
   */
  public EmailAddress getEmail() {
    return email;
  }

  /**
   * @return the picture
   */
  public String getPicture() {
    return picture;
  }

  /**
   * @return the roles
   */
  public Set<String> getRoles() {
    return roles;
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, id, name, picture, roles, username);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserDefinition other = (UserDefinition) obj;
    return Objects.equals(email, other.email) && Objects.equals(id, other.id)
        && Objects.equals(name, other.name) && Objects.equals(picture, other.picture)
        && Objects.equals(roles, other.roles) && Objects.equals(username, other.username);
  }

  @Override
  public String toString() {
    return "UserDefinition [id=" + id + ", username=" + username + ", name=" + name + ", email="
        + email + ", picture=" + picture + ", roles=" + roles + "]";
  }
}
