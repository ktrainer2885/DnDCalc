package spells;

import static common.Commands.rollX;

public class ClericSpells {

    static public int cureWounds(int abSpellMod){
        return rollX(8) + abSpellMod;
    }

}
