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

import static java.util.Objects.requireNonNull;
import java.security.Principal;
import java.util.Objects;
import io.toolforge.spi.model.AccountId;

public class CurrentUser implements Principal {
  public static CurrentUser of(String token, UserDefinition account) {
    return new CurrentUser(token, account);
  }

  private final String token;
  private final UserDefinition user;

  public CurrentUser(String token, UserDefinition user) {
    this.token = requireNonNull(token);
    this.user = requireNonNull(user);
  }

  /**
   * @return the token
   */
  public String getToken() {
    return token;
  }

  /**
   * @return the account
   */
  public UserDefinition getUser() {
    return user;
  }

  public AccountId getAccountId() {
    return getUser().getId();
  }

  @Override
  public String getName() {
    return user.getUsername().toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, token);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CurrentUser other = (CurrentUser) obj;
    return Objects.equals(user, other.user) && Objects.equals(token, other.token);
  }

  @Override
  public String toString() {
    return "CurrentUser [token=" + token + ", account=" + user + "]";
  }
}
