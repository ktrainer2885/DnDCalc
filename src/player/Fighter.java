package player;

import common.Creature;
import monster.Monster;

import java.util.ArrayList;
import java.util.Collections;

import static common.Ability.FightingStyle;
import static common.Ability.secondWind;
import static common.Commands.*;

public class Fighter extends Player {
    
    protected int style;
    protected int secondWindUses;

    public Fighter() {

        this.style = FightingStyle();
        //genAttributes();
        genPriorityAttributes();
        this.hp = 10 + getConMod(); // starting HP
        this.maxHp = hp;
        this.prof = 2; // Starting Bonus Profeciency
        setWeaponRoll();
        setArmorClass();
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();
        this.secondWindUses = 1;
        this.level = 1;
    }

    // Modifier for Attack Rolls if Fighter Style is Archery and is equipped with a Ranged weapon
    public int archery() {
        if (this.style == 1 && this.getWeapon().getWeaponType().equals("R")) {
            return 2;
        }
        else {
            return 0;
        }
    }
        
    // Modifier for AC if Fighter Style is Defense
    public int defense() {
        if (this.style == 2) {
            return 1;
        }
        else {
            return 0;
        }
    }

    // Modifier for Attack Rolls if Fighter is Dueling and is equipped with a single handed weapon
    public int dueling() {
        if (this.style == 3 && this.getWeapon().getWeaponType().equals("M") && this.getWeapon().getWeaponProperty().equals("1H")) {
            System.out.println("Dueling");
            return 2;
        }
        else {
            return 0;
        }
    }

    public int greatWeapon() {
        if (this.style == 4 && this.getWeapon().getWeaponType().equals("M") && this.getWeapon().getWeaponProperty().equals("2H")) {
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public void setArmorClass() {
        if (this.getArmor() == null) {
            this.ac = 18 + defense(); // chain mail and shield
        }
        else {
            this.ac = this.getArmor().getArmorClass() + defense();
        }
    }

    @Override
    public void setWeaponRoll() {
        if (this.getWeapon() == null) {
            this.weap = "1d8"; //Longsword damage
        }
        else {
            this.weap = this.getWeapon().getWeaponRoll();
        }
    }

    @Override
    /* Leveling Up (Move to Player.java Create Abstract for each Character Class)

        Increased Hit Point Maximum: Current Hit Points + (Half Current Hit Points + 1) OR Roll Hit Die
        Increased Proficiency: (1-4) 2 (5-8) 3 (9-12) 4 (13-16) 5 (17-20) 6

    */
    public void levelUp() {
         setLevel(getLevel() +1);

        // Ricardo's Version Does not level up. It checks level.
/*        // Hit Points
        if (level > 1) {
            // Start From Level 2
            for (int x = 2; x <= level; x++) {
                // Dice type different for other classes
                this.setHp(hp + rollHP(1,10,this.getConMod()));
            }
            */

        if (level > 1 && level < 20) {
            this.setHp(this.getHp() + rollHP(1,10,this.getConMod()));
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

        setStr(attr.remove(0));
        setCon(attr.remove(0));
        setDex(attr.remove(0));
        setWis(attr.remove(0));
        setIntel(attr.remove(0));
        setCha(attr.remove(0));

    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {

        // 2nd wind is a bonus action can still attack. Only does it if less than 50% life left
        if( 50 > hpPercent() && secondWindUses > 0) {
            recieveHealing(secondWind(10,level));
        }

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
