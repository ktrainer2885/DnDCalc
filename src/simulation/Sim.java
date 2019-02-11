package simulation;

import common.Creature;
import monster.Goblin;
import player.Fighter;

import java.util.ArrayList;
import java.util.Collections;

public class Sim {

    private ArrayList<Creature> party;
    private ArrayList<Creature> encounter;
    private ArrayList<Creature> initSort;

    private int partySize;
    private int encounterSize;
    private int simIterations;
    private int winNum;
    private double winRate;

    public Sim(int partySize, int encounterSize, int simIterations){
        this.party = new ArrayList<>();
        this.encounter = new ArrayList<>();
        this.initSort = new ArrayList<>();
        this.simIterations = simIterations;
        this.partySize = partySize;
        this.encounterSize = encounterSize;
        this.winNum = 0;
    }
    
    // Populating Encounter Group
    private void newEncounter( int encounterSize){

        //System.out.println("Goblin Initiatives");
        for (int i = 0; i < encounterSize; i++) {
            encounter.add(new Goblin());
            //System.out.println(encounter[i].getInit());
        }
    }
    
    // Populating Party Group
    private void newParty(int partySize){
        //System.out.println("Party Initiatives");
        for (int i = 0; i < partySize; i++) {
            party.add(new Fighter());
            //System.out.println(party[i].getInit());
        }
    }
    
    // Checking If Group is Alive
    public boolean checkGroupAlive(ArrayList<Creature> group) {
        for (Creature g : group) {
            if (g.isAlive()) {
                return g.isAlive();
            }
        }
        return false;
    }
    
    public void setCombat(){

        for (Creature p: party) {
            p.generateInitiative();
        }

        for (Creature e : encounter) {
            e.generateInitiative();
        }

        initSort.addAll(party);
        initSort.addAll(encounter);
        Collections.sort(initSort);
        Collections.reverse(initSort);


        // do inititive
        // todo implement initiative
        
        while (checkGroupAlive(party) && checkGroupAlive(encounter)) {

            // do a round
            round();
        }
    }

   public void combat(ArrayList<Creature> attacker, ArrayList<Creature> defender){

       for (Creature a : attacker) {
           if (!a.isAlive()) {
               break;
            }

           for (Creature d : defender) {
               if (d.isAlive()) {
                   // Calling the helper function located in the Creature Class
                   a.singleCombat(a, d);
                   break;
                }
            }
        }
    }

    public void round() {
        // encounter attacks party
        // check t see if party is alive(single)
        // attack if alive
        // continue down the list until nomore encouter attacks

        combat(encounter,party);

        // party attacks encounter
        // check to see if encouter is alive
        // attack if alive
        //contine down list untl no more party attacks

        combat(party,encounter);
    }

    public void simulation() {

        for (int i = 0; i < simIterations; i++) {
            //System.out.println(i);
            newEncounter(encounterSize);
            newParty(partySize);

            setCombat();
            if(checkGroupAlive(party)){
                winNum++;
            }

            encounter.clear();
            party.clear();
        }

       calcWinRate(winNum, simIterations);

        System.out.println("Win Rate: " + String.format("%.2f", winRate) +"%");
    }
    
    private void calcWinRate(int wins, int simIterations)
        { winRate = ((double)wins/simIterations) * 100; }
}


// todo 1: combat between individuals


// todo 2: combat between groups
