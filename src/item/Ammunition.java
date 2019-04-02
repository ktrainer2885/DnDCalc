package item;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import common.Item;
import common.AmmunitionDeserializer;

@JsonDeserialize(using = AmmunitionDeserializer.class)
public class Ammunition extends Item {
    // Private Data Types
    String value;
    Boolean ammunition;

    public Ammunition() {}

    public Ammunition(Ammunition copy) {
        this.name = copy.name;
        this.type = copy.type;
        this.ammunition = copy.ammunition;
        this.rarity = copy.rarity;
        this.value = copy.value;
        this.weight = copy.weight;
    }

    public Ammunition(String name, String type, String rarity, String value, String weight, Boolean ammunition) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.value = value;
        this.weight = weight;

        this.ammunition = ammunition;
    }
}