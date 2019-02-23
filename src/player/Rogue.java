package player;

import common.Creature;
import monster.Monster;

import java.util.ArrayList;

import static common.Commands.*;

public class Rogue extends Player {

    /* Implement Sneak Attack

            Attack Modifier =   Melee:          Strength Modifier
                                Melee/Finesse:  Strength or Dexterity Modifier
                                Ranged:         Dexterity Modifier
    */
    public Rogue() {
        genAttributes();
        this.hp = 5 + getConMod();     // Starting Hp
        this.prof = 2;                 // Starting Prof
        this.weap = "1d8";             // Rapiers Finesse Type
        this.ac = 11;                  // Light Armor: Leather
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();
    }

    @Override
    public void genPriorityAttributes() {

    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {


        // attack
        Monster lowest = lowestMonHP(combatList);

        // If there is no monster, just return, else attack
        if (lowest == null) {
            return;
        }
        else {
            singleCombat(lowest);
        }
    }
}
