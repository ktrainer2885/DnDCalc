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


}
// todo 1: combat between individuals


// todo 2: combat between groups