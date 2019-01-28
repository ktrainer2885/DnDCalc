package simulation;

import common.Creature;
import monster.Goblin;
import player.Fighter;

public class Sim {

    private Fighter[] party;
    private Goblin[] encounter;

    private int partySize;
    private int encounterSize;
    private int simIterations;
    private int winNum;
    private double winRate;

    public Sim(int partySize, int encounterSize, int simIterations){

        this.party = new Fighter[partySize];
        this.encounter = new Goblin[encounterSize];
        this.simIterations = simIterations;
        this.partySize = partySize;
        this.encounterSize = encounterSize;
        this.winNum = 0;
    }

    private void newEncounter( int encounterSize){
        for (int i = 0; i < encounterSize; i++) {
            encounter[i] = new Goblin();
        }
    }

    private void newParty(int partySize){
        for (int i = 0; i < partySize; i++) {
            party[i] = new Fighter();
        }
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

        while (checkGroupAlive(party) && checkGroupAlive(encounter)) {

            // do a round
            round();
        }
    }

    public void singleCombat(Creature attacker, Creature defender) {
        if( checkHit(attacker,defender)){
            hit(attacker,defender);
        }
    }

    public void round() {
        // encounter attacks party
        // check t see if party is alive(single)
        // attack if alive
        // continue down the list until nomore encouter attacks

        for (int i = 0; i < encounter.length; i++) {
            if(!encounter[i].isAlive()){
                break;
            }

            for (int j = 0; j < party.length; j++) {
                if (party[j].isAlive()) {
                    singleCombat(encounter[i], party[j]);
                    break;
                }
            }
        }


        // party attacks encounter
        // check to see if encouter is alive
        // attack if alive
        //contine down list untl no more party attacks

        for (int i = 0; i < party.length; i++) {
            for (int j = 0; j < encounter.length; j++) {
                if(!party[i].isAlive()){
                    break;
                }
                if (encounter[j].isAlive()) {
                    singleCombat(party[i], encounter[j]);
                    break;
                }
            }
        }
    }

    private void calcWinRate(int wins, int simIterations){
        winRate = ((double)wins/simIterations) * 100;
    }

    public void simulation() {

        for (int i = 0; i < simIterations; i++) {

            newEncounter(encounterSize);
            newParty(partySize);

            combat();
            if(checkGroupAlive(party)){
                winNum++;
            }
        }

       calcWinRate(winNum, simIterations);

        System.out.println("Win Rate: " + winRate +"%");

    }
}


// todo 1: combat between individuals


// todo 2: combat between groups
