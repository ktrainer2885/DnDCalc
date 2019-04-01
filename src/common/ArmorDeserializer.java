package common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import item.Armor;

import java.io.IOException;

public class ArmorDeserializer extends StdDeserializer<Item> {

    public ArmorDeserializer() { this(null); }

    public ArmorDeserializer(Class<?> vc) { super(vc); }

    @Override
    public Armor deserialize (JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Armor armorObject;

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        armorObject = new Armor();
        return armorObject;
    }

}
