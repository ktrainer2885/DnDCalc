package Spells;

import static common.Commands.rollX;

public class ClericSpells {

    public int cureWounds(int abSpellMod){
        return rollX(8) + abSpellMod;
    }

}
