package common;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

import static common.Commands.roll20;

public class Creature implements Comparable<Creature> {

    protected int hp;               // Health Points
    protected int prof;             // Proficiency
    protected String weap;          // Weapon (Ex: 1d6)
    protected String[] diceNum;     // Number of dice
    protected int damConst;         // Damage constant: StrMod, DexMod, Magic etc
    protected int ac;               // Armor Class
    protected int init;             // Initiative

    protected boolean alive;        // Alive Status

    protected int str;              // Strength
    protected int dex;             // Dexterity
    protected int con;              // Constitution
    protected int intel;            // intelligence
    protected int wis;              // wisdom
    protected int cha;              // charisma



    // used to compare initiative between creatures.
    @Override
    public int compareTo(Creature o2){
        if(this.getInit() < o2.getInit()){
            return -1;
        }
        if(this.getInit() == o2.getInit()){
            return 0;
        }
        else {              //(o1.getInit() > o2.getInit()){
            return 1;
        }
    }

    public int getStr() {
        return str;
    }

    public int getStrMod() {
        return  (str - 10) / 2;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public int getDexMod() {
        return  (dex - 10) / 2;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getCon() {
        return con;
    }

    public int getConMod() {
        return  (con - 10) / 2;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getIntel() {
        return intel;
    }

    public int getIntelMod() {
        return  (intel - 10) / 2;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public int getWis() {
        return wis;
    }

    public int getWisMod() {
        return  (wis - 10) / 2;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getCha() {
        return cha;
    }

    public int getChaMod() {
        return  (cha - 10) / 2;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    public boolean isAlive() {
        return alive;
    }

    private void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }
    

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getProf() {
        return prof;
    }

    public void setProf(int prof) {
        this.prof = prof;
    }

    public String getWeap() {
        return weap;
    }

    public void setWeap(String weap) {
        this.weap = weap;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }
    
    // Identical Monsters = Different Initiatives
    public void generateInitiative() {
        setInit(roll20()+ getDexMod());
    }

    public int attack(){
        return roll20() + prof;
    }

    /*public  int attackDamage(int constant) {
        return attackDamage() + constant;
    }*/

    public int attackDamage(int result){
        // Explanation for Result:
        //  1: Critical
        //  2: Normal
        int damage;
        if (damConst != 0) {
            damage = damConst;
        }
        else {
            damage = 0;
        }
        
        // Refractor
        damage += generateDamage(diceNum);
        
        // If Critical Hit, double damage
        if (result == 1)
            damage += generateDamage(diceNum);
        
        // Else Normal Hit, no additional damage
        
        return damage;
    }

    public void receiveDamage(int damage){
        setHp(getHp()-damage);

        if(getHp() <= 0 ) {
            setAlive(false);
        }
    }

    protected void setDamageDice(){
        diceNum = weap.split("d");
    }
    
    // Helper Function Used to Generate Damage
    int generateDamage(String[] diceNum) {
        
        int damage = 0;
        
        for (int i = 0; i < Integer.parseInt(diceNum[0]); i++)
            damage += ThreadLocalRandom.current().nextInt(1, Integer.parseInt(diceNum[1])+1);
        
        return damage;
    }

    public void setAttributes(int str, int dex, int con, int intel, int wis, int cha){

        setStr(str);
        setDex(dex);
        setCon(con);
        setIntel(intel);
        setWis(wis);
        setCha(cha);
    }
}
