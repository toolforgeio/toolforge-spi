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
package io.toolforge.spi.auth.util;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toSet;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import io.toolforge.spi.auth.UserDefinition;
import io.toolforge.spi.model.AccountId;
import io.toolforge.spi.model.EmailAddress;
import io.toolforge.spi.model.HumanName;
import io.toolforge.spi.model.Username;

public final class Users {
  private Users() {}

  public static final String ROLE_SUPERUSER = "superuser";

  public static final String USERNAME_CLAIM_NAME = UserInfo.PREFERRED_USERNAME_CLAIM_NAME;

  public static final String NAME_CLAIM_NAME = UserInfo.NAME_CLAIM_NAME;

  public static final String EMAIL_CLAIM_NAME = UserInfo.EMAIL_CLAIM_NAME;

  public static final String PICTURE_CLAIM_NAME = UserInfo.PICTURE_CLAIM_NAME;

  public static final String ROLES_CLAIM_NAME = "roles";

  public static UserDefinition fromClaims(JWTClaimsSet claims) throws IOException {
    UserDefinition result;
    try {
      AccountId id = AccountId.fromString(claims.getSubject());
      Username username = Username.fromString(claims.getStringClaim(USERNAME_CLAIM_NAME));
      HumanName name = HumanName.fromString(claims.getStringClaim(NAME_CLAIM_NAME));
      EmailAddress email = EmailAddress.fromString(claims.getStringClaim(EMAIL_CLAIM_NAME));
      String picture = claims.getStringClaim(PICTURE_CLAIM_NAME);
      Set<String> roles = unmodifiableSet(
          Arrays.stream(claims.getStringArrayClaim(ROLES_CLAIM_NAME)).collect(toSet()));
      result = UserDefinition.of(id, username, name, email, picture, roles);
    } catch (ParseException e) {
      throw new IOException("Failed to parse JWT", e);
    }
    return result;
  }

  public static JWTClaimsSet toClaims(UserDefinition d) {
    return appendClaims(new JWTClaimsSet.Builder(), d).build();
  }

  public static JWTClaimsSet.Builder appendClaims(JWTClaimsSet.Builder b, UserDefinition d) {
    return b.subject(d.getId().toString()).claim(USERNAME_CLAIM_NAME, d.getUsername().toString())
        .claim(NAME_CLAIM_NAME, d.getName().toString())
        .claim(EMAIL_CLAIM_NAME, d.getEmail().toString()).claim(PICTURE_CLAIM_NAME, d.getPicture())
        .claim(ROLES_CLAIM_NAME, new ArrayList<>(d.getRoles()));
  }

  public static boolean superuser(UserDefinition d) {
    return d.getRoles().contains(ROLE_SUPERUSER);
  }
}
