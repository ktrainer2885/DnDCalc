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
        String name;
        String type;
        Boolean armor;
        String rarity;
        //String value;
        String weight;
        Integer ac;

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        // Rest assured, only armor items will be allowed here
        if (!jsonNode.has("armor")) {
            return null;    // null objects in array will not be added to array list
        }

        name = (String) jsonNode.get("name").asText();
        type = (String) jsonNode.get("type").asText();
        armor = (Boolean) jsonNode.get("armor").asBoolean();
        rarity = (String) jsonNode.get("rarity").asText();
        //value = (String) jsonNode.get("value").asText();
        weight = (String) jsonNode.get("weight").asText();
        ac = (Integer) jsonNode.get("ac").asInt();

        armorObject = new Armor(name, type, rarity, /*value,*/ weight, armor, ac);
        return armorObject;
    }

}
