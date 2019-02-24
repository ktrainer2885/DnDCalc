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
        this.hp = 6 + getConMod();     // Starting Hp
        this.maxHp = hp;
        this.prof = 2;                 // Starting Prof
        this.weap = "1d8";             // Rapiers Finesse Type
        this.ac = 11 + getDexMod();    // Light Armor: Leather and dex mod
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
            if (allyAlive(combatList)) {
                // Sneak Attack
                sneakAttackDamage();
            }
            singleCombat(lowest);
        }
    }

    // Used to check if any ally is alive to assist rogue in sneak attack
    // Does not count the rogue it self
    public boolean allyAlive(ArrayList<Creature> combatList) {
        int allyCount = 0;
        // Check if Ally is Alive
        // Later should return false if no melee players
        for (Creature i : combatList) {
            if (allyCount > 1) {
                return true;
            }
            if (i instanceof Player) {
                allyCount++;
            }
        }
        // No Ally is Alive
        return false;
    }
}
