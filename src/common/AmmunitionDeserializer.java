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
        String name;
        String type;
        String rarity;
        //String value;
        String weight;
        Boolean ammunition;

        // Rest assured, only ammunition items will be allowed here
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        if (!jsonNode.has("ammunition")) {
            return null;    // null objects in array will not be added to array list
        }
        name = (String) jsonNode.get("name").asText();
        type = (String) jsonNode.get("type").asText();
        rarity = (String) jsonNode.get("rarity").asText();
        //value = (String) jsonNode.get("value").asText();
        weight = (String) jsonNode.get("weight").asText();
        ammunition = (Boolean) jsonNode.get("ammunition").asBoolean();

        ammunitionObject = new Ammunition(name, type, rarity, /*value,*/ weight, ammunition);
        return ammunitionObject;
    }

}
