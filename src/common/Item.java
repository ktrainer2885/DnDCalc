package common;

import java.util.ArrayList;
import static common.Commands.*;

public abstract class Item {

    protected String name;
    protected String type;
    protected String rarity;
    protected String weight;

    public String getName() { return name; }

}