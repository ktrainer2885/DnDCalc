package player;

import common.Commands;
import common.Creature;
import monster.Monster;
import spells.ClericSpells;

import java.util.ArrayList;

import static common.Commands.*;
import static spells.ClericSpells.*;

public class Cleric extends Player {

    int levelOneSpell;

    public Cleric(){

        //genAttributes();
        genPriorityAttributes();
        this.hp = 8 + getConMod(); // starting HP
        this.maxHp = hp;
        this.prof = 2; // Starting Bonus Proficiency
        this.weap = "1d6";  // Mace bludgeoning
        this.ac = 18;  // Scale mail and shield
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();
        this.levelOneSpell = 2;

    }

    @Override
    /* Leveling Up (Move to Player.java Create Abstract for each Character Class)

        Increased Hit Point Maximum: Current Hit Points + (Half Current Hit Points + 1) OR Roll Hit Die
        Increased Proficiency: (1-4) 2 (5-8) 3 (9-12) 4 (13-16) 5 (17-20) 6

    */
    public void levelUp() {
        int level = this.getLevel();
        int hp = this.getHp();

        // Hit Points
        if (level > 1) {
            // Start From Level 2
            for (int x = 2; x <= level; x++) {
                // Dice type different for other classes
                this.setHp(hp + rollHP(1,8,this.getConMod()));

            }
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

        setWis(attr.remove(0));
        setCon(attr.remove(0));
        setStr(attr.remove(0));
        setIntel(attr.remove(0));
        setDex(attr.remove(0));
        setCha(attr.remove(0));

    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {

        Player playLowest = lowestPlayHP(combatList);
        Monster monLowest = lowestMonHP(combatList);

        if( 50 > Commands.hpPercent(playLowest) && levelOneSpell > 0) {
            playLowest.recieveHealing(cureWounds(getWisMod()));
            levelOneSpell--;
        }
        else {
            if (monLowest == null) {
                return;
            }
            singleCombat(monLowest);
        }

    }
}
