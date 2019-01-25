package common;

import java.util.concurrent.ThreadLocalRandom;

import static common.Commands.roll20;

public class Creature {

    protected int hp;
    protected int prof;
    protected String weap;
    protected String[] diceNum;
    protected int ac;
    protected int init;
    protected boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
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

    public int attack(){
        return roll20() + prof;
    }

    public int attackDamage(){

        int damage = 0;

        for(int i = 0; i < Integer.parseInt(diceNum[0]); i++){
            damage = damage + ThreadLocalRandom.current().nextInt(1, Integer.parseInt(diceNum[1]));
        }

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
}
