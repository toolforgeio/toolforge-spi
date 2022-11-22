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
package io.toolforge.spi.model.serialization;

import java.io.IOException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.toolforge.spi.model.ArgumentValue;

public class ArgumentValueDeserializer extends StdDeserializer<ArgumentValue> {
  private static final long serialVersionUID = -7966401345658854377L;

  protected ArgumentValueDeserializer() {
    super(ArgumentValue.class);
  }

  @Override
  public ArgumentValue deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ArgumentValue result;

    switch (p.currentToken()) {
      case VALUE_FALSE:
        result = ArgumentValue.FALSE;
        break;
      case VALUE_NULL:
        result = ArgumentValue.NULL;
        break;
      case VALUE_NUMBER_FLOAT:
      case VALUE_NUMBER_INT:
        result = new ArgumentValue(p.getDecimalValue());
        break;
      case VALUE_STRING:
        result = new ArgumentValue(p.getValueAsString());
        break;
      case VALUE_TRUE:
        result = ArgumentValue.TRUE;
        break;
      case START_ARRAY:
      case START_OBJECT:
        throw JsonMappingException.from(p, "expected a scalar value");
      case END_ARRAY:
      case END_OBJECT:
      case FIELD_NAME:
      case NOT_AVAILABLE:
      case VALUE_EMBEDDED_OBJECT:
      default:
        throw JsonMappingException.from(p, "unexpected token");
    }

    return result;
  }

  @Override
  public ArgumentValue getNullValue(DeserializationContext ctxt) throws JsonMappingException {
    return ArgumentValue.NULL;
  }
}
