package monster;

import common.Creature;
import java.util.concurrent.ThreadLocalRandom;

import static common.Commands.roll20;

public class Goblin extends Creature {

    private int attackProf;


    Goblin(){
        this.hp = ThreadLocalRandom.current().nextInt(1,7)+ ThreadLocalRandom.current().nextInt(1,7);
        this.ac = 15; //leather armor shield
        this.attackProf = 4;
        this.weap = "1d6";
        setDamageDice();
        this.alive = true;
    }


    public int getAttackProf() {
        return attackProf;
    }

    public void setAttackProf(int attackProf) {
        this.attackProf = attackProf;
    }

    public int attack(){
        return roll20() + attackProf;
    }


}
