package cn.spark.chipro.core.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author liliguang
 * @description
 * @date 2019/5/16
 */
public class BootOAuthExceptionJacksonSerializer extends StdSerializer<BootOAuth2Exception> {

    protected BootOAuthExceptionJacksonSerializer() {
        super(BootOAuth2Exception.class);
    }

    @Override
    public void serialize(BootOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("code", -1);
        String errorMessage = value.getMessage();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
        jgen.writeStringField("msg", errorMessage);
        jgen.writeStringField("datas", "");
        if (value.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        jgen.writeEndObject();
    }


}
