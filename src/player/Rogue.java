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
       // genAttributes();
        genPriorityAttributes();
        this.hp = 6 + getConMod();     // Starting Hp
        this.maxHp = hp;
        this.prof = 2;                 // Starting Prof
        this.weap = "1d8";             // Rapiers Finesse Type
        this.ac = 11 + getDexMod();    // Light Armor: Leather and dex mod
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();
        this.level = 1;
    }

    @Override
    /* Leveling Up (Move to Player.java Create Abstract for each Character Class)

        Increased Hit Point Maximum: Current Hit Points + (Half Current Hit Points + 1) OR Roll Hit Die
        Increased Proficiency: (1-4) 2 (5-8) 3 (9-12) 4 (13-16) 5 (17-20) 6

    */
    public void levelUp() {
        setLevel(getLevel() +1);

/*        // Hit Points
        if (level > 1) {
            // Start From Level 2
            for (int x = 2; x <= level; x++) {
                // Dice type different for other classes
                this.setHp(hp + rollHP(1,6,this.getConMod()));

            }
        }*/

        if (level > 1 && level < 20) {
            this.setHp(this.getHp() + rollHP(1,6,this.getConMod()));
            this.setMaxHp(this.getHp());
        }
        // Proficiency
        if (level >= 1 && level <= 4) {
            this.setProf(2);
        }
        if (level >= 5 && level <= 8) {
            this.setProf(3);
        }
        if (level >= 9 && level <= 12) {
            this.setProf(4);
        }
        if (level >= 13 && level <= 16) {
            this.setProf(5);
        }
        if (level >= 17 && level <= 20) {
            this.setProf(6);
        }
    }

    @Override
    public void genPriorityAttributes() {
        ArrayList<Integer> attr = poolAttribute();

        setDex(attr.remove(0));
        setCon(attr.remove(0));
        setStr(attr.remove(0));
        setIntel(attr.remove(0));
        setWis(attr.remove(0));
        setCha(attr.remove(0));

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
