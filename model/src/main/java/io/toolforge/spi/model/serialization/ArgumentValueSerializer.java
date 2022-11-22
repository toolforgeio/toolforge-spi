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
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.toolforge.spi.model.ArgumentValue;

public class ArgumentValueSerializer extends StdSerializer<ArgumentValue> {
  private static final long serialVersionUID = 3329904785482275556L;

  protected ArgumentValueSerializer() {
    super(ArgumentValue.class);
  }

  @Override
  public void serialize(ArgumentValue value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    if (value.isNull()) {
      gen.writeNull();
    } else if (value.isNumber()) {
      gen.writeNumber(value.numberVal());
    } else if (value.isString()) {
      gen.writeString(value.stringVal());
    } else if (value.isBoolean()) {
      gen.writeBoolean(value.booleanVal());
    } else {
      throw new AssertionError(value.getValue());
    }
  }
}
