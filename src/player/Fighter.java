package player;

import common.Creature;

import static common.Commands.genAttribute;

public class Fighter extends Player {

    public Fighter(){

        genAttributes();
        this.hp = 10 + getConMod(); // starting HP
        this.prof = 2; // Starting Bonus Profeciency
        this.weap = "1d8"; //Longsword damage
        this.ac = 18; // chainmail and shield
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();

    }

}
