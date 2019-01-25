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
}
// todo 1: combat between individuals
// todo 2: combat between groups