package common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import item.Ammunition;

import java.io.IOException;

public class AmmunitionDeserializer extends StdDeserializer<Item> {

    public AmmunitionDeserializer() { this(null); }

    public AmmunitionDeserializer(Class<?> vc) { super(vc); }

    @Override
    public Ammunition deserialize (JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Ammunition ammunitionObject;

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        ammunitionObject = new Ammunition();
        return ammunitionObject;
    }
}
