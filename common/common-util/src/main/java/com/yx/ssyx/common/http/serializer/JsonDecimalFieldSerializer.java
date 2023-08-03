package com.yx.ssyx.common.http.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * JsonDecimalSerializer
 *
 */
public class JsonDecimalFieldSerializer extends JsonSerializer<BigDecimal> implements ContextualSerializer {
    protected DecimalFormat decimalFormat;


    public JsonDecimalFieldSerializer() {
    }

    public JsonDecimalFieldSerializer(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        {
            final String text;
            if (gen.isEnabled(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)) {
                final BigDecimal bd = (BigDecimal) value;
                text = bd.setScale(6, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
            } else {
                text = value.setScale(6, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toString();
            }
            gen.writeString(text);
//            try {
//                BigDecimalPrecision annotation = gen.getOutputContext().getCurrentValue().getClass().getDeclaredField(gen.getOutputContext().getCurrentName()).getAnnotation(BigDecimalPrecision.class);
//                int scale = annotation != null ? annotation.scale() : 4;
//                final String text;
//                if (gen.isEnabled(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)) {
//                    final BigDecimal bd = (BigDecimal) value;
//                    text = bd.setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
//                } else {
//                    text = value.setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toString();
//                }
//                gen.writeString(text);
//            } catch (NoSuchFieldException e) {
//                throw new RuntimeException(e);
//            }
        }
    }


    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException {

        JsonFormat.Value format = findFormatOverrides(prov, property, handledType());
        if (format == null) {
            return this;
        }

        if (format.hasPattern()) {
            DecimalFormat decimalFormat = new DecimalFormat(format.getPattern());
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            return new JsonDecimalFieldSerializer(decimalFormat);
        }

        return this;
    }

    protected JsonFormat.Value findFormatOverrides(SerializerProvider provider,
                                                   BeanProperty prop, Class<?> typeForDefaults) {
        if (prop != null) {
            return prop.findPropertyFormat(provider.getConfig(), typeForDefaults);
        }
        return provider.getDefaultPropertyFormat(typeForDefaults);
    }
}
