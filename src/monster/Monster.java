package monster;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import common.Creature;
import common.MonsterDeserializer;

import java.util.ArrayList;

import static common.Commands.rollHP;

@JsonDeserialize(using = MonsterDeserializer.class)
public class Monster extends Creature {
    private String name;


    public Monster(){}

    public Monster(String name, Integer ac, Integer hpDiceNum, Integer hpDiceType, Integer hpConstant, Integer prof,
                   Integer str, Integer dex, Integer con, Integer intel, Integer wis, Integer cha,
                   String weapon, Integer damCont){

        this.name = name;
        setAttributes(str,dex,con,intel,wis,cha);
        this.hp = rollHP(hpDiceNum,hpDiceType, hpConstant);
        this.setMaxHp(this.hp);
        this.ac = ac;
        this.prof = prof;
        this.weap = weapon;
        setDamageDice();
        this.alive = true;
        this.damConst = damCont;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {

    }
}
