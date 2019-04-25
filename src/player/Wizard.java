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
        this.level = 1;
        this.hp = 6 + getConMod(); // starting HP
        this.maxHp = hp;
        this.prof = 2; // Starting Bonus Proficiency
        setWeaponRoll();
        setArmorClass();
        setDamageDice();
        this.alive = true;
        this.damConst = getIntelMod();
        this.spellLevelOne = 2;
    }

    @Override
    public void setArmorClass() {
        if (this.getArmor() == null) {
            this.ac = 10 + this.getDexMod();  // No armor: Base AC
        }
        else {
            this.ac = this.getArmor().getArmorClass();
        }
    }

    @Override
    public void setWeaponRoll() {
        if (this.getWeapon() == null) {
            this.weap = "1d6";  // Quarterstaff bludgeoning
        }
        else {
            this.weap = this.getWeapon().getWeaponRoll();
        }
    }

    @Override
    public void levelUp() {
        setLevel(getLevel() +1);

/*
        // Hit Points
        if (level > 1) {
            // Start From Level 2
            for (int x = 2; x <= level; x++) {
                // Dice type different for other classes
                this.setHp(hp + rollHP(1,6,this.getConMod()));

            }
        }
*/

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
