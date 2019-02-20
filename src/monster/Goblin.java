package monster;

import common.Creature;

import java.util.ArrayList;

import static common.Commands.rollHP;

public class Goblin extends Monster {

    public Goblin(){

        setAttributes(8,14,10,10,8,8 );
        this.hp = rollHP(2,6);
        this.ac = 15; //leather armor shield
        this.prof = 4;
        this.weap = "1d6";
        setDamageDice();
        this.alive = true;
        this.damConst = 2;

    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {

    }
}
