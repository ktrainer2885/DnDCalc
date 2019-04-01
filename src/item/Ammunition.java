package item;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import common.Item;
import common.AmmunitionDeserializer;

@JsonDeserialize(using = AmmunitionDeserializer.class)
public class Ammunition extends Item {
    // Private Data Types
    boolean ammunition;

    public Ammunition() {}

    public Ammunition(Ammunition copy) {
        // Set data types
        this.ammunition = copy.ammunition;
    }

    public Ammunition(boolean ammunition /* Data Types in parameter*/) {
        // Set Private data types with parameter data types
        this.ammunition = ammunition;
    }
}