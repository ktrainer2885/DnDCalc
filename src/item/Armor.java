package item;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import common.Item;
import common.ArmorDeserializer;

@JsonDeserialize(using = ArmorDeserializer.class)
public class Armor extends Item {

    //String value;
    Boolean armor;
    Integer ac;

    public Armor() {}

    public Armor(Armor copy) {
        this.name = copy.name;
        this.type = copy.type;
        this.rarity = copy.rarity;
        //this.value = copy.value;
        this.weight = copy.weight;

        this.armor = copy.armor;
        this.ac = copy.ac;
    }

    public Armor(String name, String type, String rarity, /*String value,*/ String weight, Boolean armor, Integer ac) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        //this.value = value;
        this.weight = weight;

        this.armor = armor;
        this.ac = ac;
    }

    public Integer getArmorClass() {
        return this.ac;
    }

    @Override public String toString() {
        return name;
    }
}
