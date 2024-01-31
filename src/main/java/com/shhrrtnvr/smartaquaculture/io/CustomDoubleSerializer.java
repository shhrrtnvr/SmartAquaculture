package com.shhrrtnvr.smartaquaculture.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomDoubleSerializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            BigDecimal bd = new BigDecimal(Double.toString(value));
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            gen.writeNumber(bd.doubleValue());
        }
    }
}
