package com.qindel.configuration;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.DecimalFormat;

/** CustomFloatSerializer. */
public class CustomFloatSerializer extends JsonSerializer<Float> {

  @Override
  public void serialize(Float value, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonGenerationException {
    if (null == value) {
      jgen.writeNull();
    } else {
      final String pattern = "#.00";
      final DecimalFormat myFormatter = new DecimalFormat(pattern);
      final String output = myFormatter.format(value);
      jgen.writeNumber(output);
    }
  }
 
}
