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

    // Checking If Individual from Group is Alive
    public boolean checkAlive(Creature individual ){
        return individual.isAlive();
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
    
    // Determine if attack is stronger than reciever's armor class or if critical hit is achieved
    public int checkHit(Creature attacker, Creature defender){
        // Roll the Attack
        int attackRoll = attacker.attack();
        // Check if Critical
        if ((attackRoll - attacker.getProf()) == 20)
            return 1;
        // Check if Attack Roll is Stronger than Defender's Armor Class
        else if (attackRoll >= defender.getAc()){
            return 2;
        }
        // Either no Critical Hit or Attack Roll is Weaker than Defender's Armor Class
        return 0;
    }
    
    // Generate Critical Hit
    public void hit(Creature attacker, Creature defender, int result) {
        // Explanation for Result:
        //  1: Critical
        //  2: Normal
        defender.receiveDamage(attacker.attackDamage(result));
    }

    public void combat(){

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

    public void singleCombat(Creature attacker, Creature defender) {
        // Explanation for Result:
        //  1: Critical
        //  2: Normal
        int result = checkHit(attacker,defender);
        
        hit(attacker,defender,result);
    }

   public  void combat(ArrayList<Creature> attacker, ArrayList<Creature> defender){

       for (Creature a : attacker) {
           if (!a.isAlive()) {
               break;
           }

           for (Creature d : defender) {
               if (d.isAlive()) {
                   singleCombat(a,d);
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


 /*       for (int i = 0; i < encounter.length; i++) {
            if(!encounter[i].isAlive()){
                break;
            }

            for (int j = 0; j < party.length; j++) {
                if (party[j].isAlive()) {
                    singleCombat(encounter[i], party[j]);
                    break;
                }
            }
        }*/


        // party attacks encounter
        // check to see if encouter is alive
        // attack if alive
        //contine down list untl no more party attacks

        combat(party,encounter);

        /*for (int i = 0; i < party.length; i++) {
            for (int j = 0; j < encounter.length; j++) {
                if(!party[i].isAlive()){
                    break;
                }
                if (encounter[j].isAlive()) {
                    singleCombat(party[i], encounter[j]);
                    break;
                }
            }
        }*/
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

            encounter.clear();
            party.clear();
        }

       calcWinRate(winNum, simIterations);

        System.out.println("Win Rate: " + String.format("%.2f", winRate) +"%");

    }
}


// todo 1: combat between individuals


// todo 2: combat between groups
