package player;

import common.Creature;
import monster.Monster;

import java.util.ArrayList;

import static common.Commands.*;
import static spells.WizardSpells.fireBolt;
import static spells.WizardSpells.magicMissile;

public class Wizard extends Player {

    int spellLevelOne;

    public Wizard(){

        //genAttributes();
        genPriorityAttributes();
        this.hp = 6 + getConMod(); // starting HP
        this.maxHp = hp;
        this.prof = 2; // Starting Bonus Proficiency
        this.weap = "1d6";  // Quarterstaff bludgeoning
        this.ac = 10 + this.getDexMod();  // No armor: Base AC
        setDamageDice();
        this.alive = true;
        this.damConst = getIntelMod();
        this.spellLevelOne = 2;
    }

    @Override
    public void genPriorityAttributes() {
        ArrayList<Integer> attr = poolAttribute();

        setIntel(attr.remove(0));
        setWis(attr.remove(0));
        setDex(attr.remove(0));
        setCon(attr.remove(0));
        setStr(attr.remove(0));
        setCha(attr.remove(0));
    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {

        Monster monHighest = highestMonHP(combatList);
        Monster monLowest = lowestMonHP(combatList);

        if (spellLevelOne > 0) {
            if(monLowest == null){
                return;
            }
            // Magic Missile auto hits.
            // make new
            monLowest.receiveDamage(magicMissile(level));
            spellLevelOne--;
        }
        else {
            if(monLowest == null){
                return;
            }
            spellCombat(monLowest,fireBolt(level));
        }
    }
}
