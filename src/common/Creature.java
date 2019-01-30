package common;

import java.util.concurrent.ThreadLocalRandom;

import static common.Commands.roll20;

public class Creature {

    protected int hp;               // Health Points
    protected int prof;             // Proficiency
    protected String weap;          // Weapon (Ex: 1d6)
    protected String[] diceNum;     // Number of dice
    protected int ac;               // Armor Class
    protected int init;             // Initiative
    protected int dext;             // Dexterity
    protected boolean alive;        // Alive Status

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
    
    public int getDext() {
        return dext;
    }
    
    public void setDext(int dext) {
        this.dext = dext;
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
        setInit(roll20()+getDext());
    }

    public int attack(){
        return roll20() + prof;
    }

    public int attackDamage(){        
        int damage = 0;
        int roll = 0;
        boolean isCritical = false;
        
        // Roll 20
        roll = roll20();
        
        // If Roll is 20, Critical Hit
        if (roll == 20)
            isCritical = true;
        
        // Refractor
        damage += generateDamage(diceNum);
        
        // If Critical Hit, double damage
        if (isCritical)
            damage += generateDamage(diceNum);

        return damage;
    }

    public void recieveDamage(int damage){
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
            damage += ThreadLocalRandom.current().nextInt(1, Integer.parseInt(diceNum[1]));
        
        return damage;
    }
}
