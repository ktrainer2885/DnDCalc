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
        String weight;
        //String technology;
        Boolean weapon;
        String weaponCategory;
        //String age;
        String dmg1;      // Will need to allow more than one of these
        String dmgType;
        String property = "1H";
        String range;
        String reload;

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        // Rest assured, only weapon items will be allowed here
        if (!jsonNode.has("weapon")) {
            return null;    // null objects in array will not be added to array list
        }


        name = (String) jsonNode.get("name").asText();
        type = (String) jsonNode.get("type").asText();
        //technology = (String) jsonNode.get("technology").asText();
        weapon = (Boolean) jsonNode.get("weapon").asBoolean();
        weaponCategory = (String) jsonNode.get("weaponCategory").asText();
        //age = (String) jsonNode.get("age").asText();
        rarity = (String) jsonNode.get("rarity").asText();

        // Some weapons have a weight tag, some do not
        if (jsonNode.has("weight")) {
            weight = (String) jsonNode.get("weight").asText();
        }
        else {
            weight = "None";
        }

        // Setting dmg1
        if (jsonNode.has("dmg1")) {
            dmg1 = (String) jsonNode.get("dmg1").asText();
        }
        else {
            dmg1 = "1d0";
        }

        // Some weapons do not have a dmgType (Ex: Net)
        if (jsonNode.has("dmgType")) {
            dmgType = (String) jsonNode.get("dmgType").asText();
        }
        else {
            dmgType = "None";
        }

        // property tag | Checks if weapon is 2 handed
        if (jsonNode.has("property")) {
            int size = jsonNode.get("property").size();
            for (int i = 0; i < jsonNode.get("property").size(); i++) {
                property = (String) jsonNode.get("property").get(i).asText();
                if (property.equals("2H")) {
                    break;
                }
            }
        }

        // property tag

        // Ranged weapons have a range tag, Melee weapons do not
        if (type.equals("R")) {
            range = (String) jsonNode.get("range").asText();
        }
        else {
            range = "0";
        }

        /*
            Ranged weapons with ammo capacity > 1 have a reload tag,
             ranged weapons with ammo capacity = 1 do not,
             melee weapons do not
        */
        if (type.equals("R") && !jsonNode.has("reload")) {
            reload = "1";
        }
        else if (type.equals("R") && jsonNode.has("reload")) {
            reload = (String) jsonNode.get("reload").asText();
        }
        else {
            reload = "None";
        }


        weaponObject = new Weapon(name, type, rarity, weight, /*technology,*/ weapon, weaponCategory, property, /*age,*/ dmg1, dmgType, range, reload);
        return weaponObject;
    }
}
