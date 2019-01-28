package simulation;

import common.Creature;
import monster.Goblin;
import player.Fighter;

public class Sim {

    private Fighter[] party;
    private Goblin[] encounter;

    Sim(int partySize, int encounterSize){

        this.party = new Fighter[partySize];
        this.encounter = new Goblin[encounterSize];
    }

    public boolean checkAlive(Creature individual ){
        return individual.isAlive();
    }

    public boolean checkGroupAlive(Creature[] group) {
        for (Creature g : group) {
            if (g.isAlive()) {
                return g.isAlive();
            }
        }
        return false;
    }

    public boolean checkHit(Creature attacker, Creature defender){
        if (attacker.attack() >= defender.getAc()){
            return true;
        }
        else {
            return false;
        }
    }

    public void hit(Creature attacker, Creature defender) {
        defender.recieveDamage(attacker.attackDamage());
    }

    public void combat(){

        // do inititive
        // todo implement initiive

        while (!checkGroupAlive(party) || !checkGroupAlive(encounter)) {

            // do a round

        }
    }

    public void singleCombat(Creature attacker, Creature defender) {
        if( checkHit(attacker,defender)){
            hit(attacker,defender);
        }
    }

    public void round() {
        // encounter attacks party

        for (int i = 0; i < encounter.length; i++) {
            for (int j = 0; j < party.length; j++) {
                if (party[j].isAlive()) {
                    singleCombat(encounter[i], party[j]);
                    break;
                }
            }
        }
        // check t see if party is alive(single)
        // attack if alive
        // continue down the list until nomore encouter attacks

        for (int i = 0; i < party.length; i++) {
            for (int j = 0; j < encounter.length; j++) {
                if (party[j].isAlive()) {
                    singleCombat(party[i], encounter[j]);
                    break;
                }
            }
        }

        // party attacks encounter
        // check to see if encouter is alive
        // attack if alive
        //contine down list untl no more party attacks

    }
}
// todo 1: combat between individuals


// todo 2: combat between groups
