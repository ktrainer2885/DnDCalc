package player;

import common.Creature;

public class Fighter extends Creature {

    public Fighter(){
        this.hp = 10; // starting HP
        this.prof = 2; // Starting Bonus Profeciency
        this.weap = "1d8"; //Longsword damage
        this.ac = 18; // chainmail and shield
        this.dext = 6;  // dexterity guess
        setDamageDice();
        generateInitiative();
        this.alive = true;
    }

}
