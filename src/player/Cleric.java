package player;

import common.Creature;

import static common.Commands.genAttribute;

public class Cleric extends Player {

    public Cleric(){

        genAttributes();
        this.hp = 8 + getConMod(); // starting HP
        this.prof = 2; // Starting Bonus Proficiency
        this.weap = "1d6";  // Mace bludgeoning
        this.ac = 18;  // Scale mail and shield
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();
    }
}
