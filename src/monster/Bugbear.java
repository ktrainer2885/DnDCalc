package monster;

import common.Creature;

import java.util.ArrayList;

import static common.Commands.rollHP;

public class Bugbear extends Monster {

    public Bugbear(){

        setAttributes(15,14,13,8,11,9 );
        this.hp = rollHP(5,8, 5);
        this.maxHp = hp;
        this.ac = 16; //Hide Armor + shield
        this.prof = 4;
        this.weap = "2d8";
        setDamageDice();
        this.alive = true;
        this.damConst = 2;
    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {

    }
}