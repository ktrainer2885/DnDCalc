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

        genAttributes();
        this.hp = 8 + getConMod(); // starting HP
        this.prof = 2; // Starting Bonus Proficiency
        this.weap = "1d6";  // Mace bludgeoning
        this.ac = 18;  // Scale mail and shield
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();
        this.levelOneSpell = 2;

    }

    @Override
    public void genPriorityAttributes() {

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
            singleCombat(monLowest);
        }

    }
}
