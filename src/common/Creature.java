package common;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static common.Commands.*;

public abstract class Creature implements Comparable<Creature> {

    protected Integer hp;               // Health Points
    protected Integer maxHp;            // Max HP
    protected Integer prof;             // Proficiency
    protected String weap;          // Weapon (Ex: 1d6)
    protected String[] diceNum;     // Number of dice
    protected Integer damConst;         // Damage constant: StrMod, DexMod, Magic etc
    protected Integer ac;               // Armor Class
    protected Integer init;             // Initiative


    protected boolean alive;        // Alive Status

    protected Integer level;            // Character level

    protected Integer str;              // Strength
    protected Integer dex;             // Dexterity
    protected Integer con;              // Constitution
    protected Integer intel;            // intelligence
    protected Integer wis;              // wisdom
    protected Integer cha;              // charisma

    protected Integer roll=0;             // saves roll for critical hit check
    protected boolean sneakAttack=false;
    protected String challengeRating;       //Monster Challenge rating



    // used to compare initiative between creatures.
    @Override
    public int compareTo(Creature o2) {
        if(this.getInit() < o2.getInit()) {
            return -1;
        }
        if(this.getInit() == o2.getInit()) {
            return 0;
        }
        else {              //(o1.getInit() > o2.getInit()){
            return 1;
        }
    }

    public Integer getLevel() { return level; }

    public void setLevel(Integer level) { this.level = level; }

    public Integer getStr() {
        return str;
    }

    public Integer getStrMod() {
        return  (str - 10) / 2;
    }

    public void setStr(Integer str) {
        this.str = str;
    }

    public Integer getDex() {
        return dex;
    }

    public Integer getDexMod() { return  (dex - 10) / 2; }

    public void setDex(Integer dex) {this.dex = dex; }

    public Integer getCon() {
        return con;
    }

    public Integer getConMod() {return  (con - 10) / 2; }

    public void setCon(Integer con) {this.con = con; }

    public Integer getIntel() {
        return intel;
    }

    public Integer getIntelMod() {
        return  (intel - 10) / 2;
    }

    public void setIntel(Integer intel) {
        this.intel = intel;
    }

    public Integer getWis() {
        return wis;
    }

    public Integer getWisMod() {
        return  (wis - 10) / 2;
    }

    public void setWis(Integer wis) {
        this.wis = wis;
    }

    public Integer getCha() {
        return cha;
    }

    public Integer getChaMod() {
        return  (cha - 10) / 2;
    }

    public void setCha(Integer cha) {
        this.cha = cha;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) { this.alive = alive; }

    public Integer getInit() {
        return init;
    }

    public void setInit(Integer init) { this.init = init; }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) { this.hp = hp; }

    public Integer getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Integer maxHp) {
        this.maxHp = maxHp;
    }

    public Integer hpPercent(){
        return (hp/maxHp) * 100;
    }

    public Integer getProf() {
        return prof;
    }

    public void setProf(Integer prof) { this.prof = prof; }

    public String getWeap() {
        return weap;
    }

    public void setWeap(String weap) { this.weap = weap; }

    public Integer getAc() {
        return ac;
    }

    public void setAc(Integer ac) {
        this.ac = ac;
    }
    
    // Generating Initiative. Add a random roll to the dexMod
    public void generateInitiative() {
        setInit(roll20()+ getDexMod());
    }

    // Roll and add prof
    // todo add the constants. Str or Dex
    public Integer attack() {
        // Roll 20 and save
        this.roll = roll20();
        return roll + prof /* + Any modifiers*/;
    }

    // Helper function used to handle sneak attack
    public void sneakAttackDamage() {
        //System.out.println("Sneak Attack!");
        this.sneakAttack = true;
    }

    // Helper function used to handle combat between two creatures
    public void singleCombat(Creature defender) {
        Integer attackRoll = attack();

        // Natural 20
        if (this.roll == 20) {
            defender.receiveDamage(this.attackDamage());
        }
        // Non-Natural 20
        else if (attackRoll >= defender.getAc()) {
            defender.receiveDamage(this.attackDamage());
        }
    }
    // Helper function used with spell. Needs to be reworked
    public void spellCombat(Creature defender, Integer spellDamage) {
        Integer attackRoll = attack();

        // Natural 20
        if (this.roll == 20) {
            defender.receiveDamage(spellDamage + spellDamage);
        }
        // Non-Natural 20
        else if (attackRoll >= defender.getAc()) {
            defender.receiveDamage(spellDamage);
        }
    }

    public Integer attackDamage() {
        Integer damage;
        if (damConst != 0) {
            damage = damConst;
        }
        else {
            damage = 0;
        }

        damage += generateDamage(diceNum);
        
        // If Critical Hit, double damage
        if (this.roll == 20) {
            damage += generateDamage(diceNum);
        }
        // If Sneak Attack, roll extra die d6
        if (this.sneakAttack) {
            damage += rollX(6);
            this.sneakAttack = false;
        }
        // Else Normal Hit, no additional damage

        return damage;
    }

    // Changes HP from damage and sets death
    public void receiveDamage(Integer damage) {
        setHp(getHp()-damage);

        if(getHp() <= 0 ) {
            setAlive(false);
            setHp(0);
        }
    }

    // CHanges HP from healing and sets to alive
    public void recieveHealing(Integer heal) {
        setHp(getHp() + heal);
        // if healed to more than maxHp set hp to MaxHp
        if (getMaxHp() < getHp()){
            setHp(getMaxHp());
        }
        setAlive(true);
    }

    protected void setDamageDice(){
        diceNum = weap.split("d");
    }
    
    // Helper Function Used to Generate Damage
    Integer generateDamage(String[] diceNum) {

        Integer damage = 0;
        
        for (Integer i = 0; i < Integer.parseInt(diceNum[0]); i++) {
            damage += ThreadLocalRandom.current().nextInt(1, Integer.parseInt(diceNum[1]) + 1);
        }
        return damage;
    }

    public void setAttributes(Integer str, Integer dex, Integer con, Integer intel, Integer wis, Integer cha){

        setStr(str);
        setDex(dex);
        setCon(con);
        setIntel(intel);
        setWis(wis);
        setCha(cha);
    }
    public abstract void chooseAction(ArrayList<Creature> combatList);
}