package Spells;

import static common.Commands.rollX;

public class WizardSpells {

    public int MagicMissle(){
        return rollX(4) + 1;
    }

}
