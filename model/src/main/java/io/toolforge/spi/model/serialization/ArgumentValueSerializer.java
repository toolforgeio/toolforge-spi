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
