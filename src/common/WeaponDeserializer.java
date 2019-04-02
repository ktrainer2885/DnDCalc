package common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import item.Weapon;

import java.io.IOException;

public class WeaponDeserializer extends StdDeserializer<Item> {

    public WeaponDeserializer() {
        this(null);
    }

    public WeaponDeserializer(Class<?> vc) { super(vc); }

    @Override
    public Weapon deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Weapon weaponObject;
        String name;
        String type;
        String rarity;
        String value;
        String weight;
        String technology;
        Boolean weapon;
        String weaponCategory;
        String age;
        //String dmg1;      Will need to allow more than one of these
        String dmgType;
        //String[] property;
        String range;
        String reload;

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        name = (String) jsonNode.get("name").asText();
        type = (String) jsonNode.get("type").asText();
        technology = (String) jsonNode.get("technology").asText();
        weapon = (Boolean) jsonNode.get("weapon").asBoolean();
        weaponCategory = (String) jsonNode.get("weaponCategory").asText();
        age = (String) jsonNode.get("age").asText();
        rarity = (String) jsonNode.get("rarity").asText();
        value = (String) jsonNode.get("value").asText();
        weight = (String) jsonNode.get("weight").asText();
        //dmg1 = (String) jsonNode.get("dmg1").asText();
        dmgType = (String) jsonNode.get("dmgType").asText();
        // property
        range = (String) jsonNode.get("range").asText();
        reload = (String) jsonNode.get("reload").asText();

        weaponObject = new Weapon(name, type, rarity, value, weight, technology, weapon, weaponCategory, age, /*dmg1,*/ dmgType, range, reload);
        return weaponObject;
    }
}
