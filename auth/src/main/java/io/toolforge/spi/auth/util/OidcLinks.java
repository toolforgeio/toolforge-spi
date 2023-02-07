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

public final class OidcLinks {
  private OidcLinks() {}

  /**
   * Generates a Cognito hosted UI token exchange URL. This endpoint allows users to exchange a code
   * for an access token. For example: https://auth.toolforge.io/oauth2/token
   * 
   * @param cognitoHostedUiBaseUrl The base URL of the Cognito hosted UI, e.g.,
   *        https://toolforge.auth.us-east-2.amazoncognito.com/
   * @return The token endpoint
   */
  public static String generateCognitoHostedUiTokenLink(String cognitoHostedUiBaseUrl) {
    return new StringBuilder().append(cognitoHostedUiBaseUrl).append("/oauth2/token").toString();
  }

  /**
   * Generates the well-known JWKs link so third parties can validate JWTs
   * 
   * @param oauthBaseUrl
   * @return
   */
  public static String generateWellKnownJwksLink(String oauthBaseUrl) {
    return new StringBuilder().append(oauthBaseUrl).append("/.well-known/jwks.json").toString();
  }
}
