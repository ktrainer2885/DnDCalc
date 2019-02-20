package monster;

import common.Creature;

import java.util.ArrayList;

import static common.Commands.rollHP;

public class Orc extends Monster {

    public Orc(){

        setAttributes(16,12,16,7,11,10 );
        this.hp = rollHP(2,8, 6);
        this.maxHp = hp;
        this.ac = 13; //Hide Armor
        this.prof = 5;
        this.weap = "1d12";
        setDamageDice();
        this.alive = true;
        this.damConst = 3;
    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {

    }
}