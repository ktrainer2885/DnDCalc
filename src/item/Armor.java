package item;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import common.Item;
import common.ArmorDeserializer;

@JsonDeserialize(using = ArmorDeserializer.class)
public class Armor extends Item {
    // Private Data Types
    boolean armor;

    public Armor() {}

    public Armor(Armor copy) {
        // Set data types
        this.armor = copy.armor;
    }

    public Armor(boolean armor /* Data Types in parameter*/) {
        // Set Private data types with parameter data types
        this.armor = armor;
    }
}
