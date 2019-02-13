package simulation;

import common.Creature;
import monster.Goblin;
import monster.Monster;
import player.Fighter;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Sim {

    private ArrayList<Creature> combatArrayList;

    private int partySize;
    private int encounterSize;
    private int simIterations;
    private int winNum;
    private double winRate;

    public Sim(int partySize, int encounterSize, int simIterations){
        this.combatArrayList = new ArrayList<>();
        this.simIterations = simIterations;
        this.partySize = partySize;
        this.encounterSize = encounterSize;
        this.winNum = 0;
    }
    
    // Populating Encounter Group
    private void newEncounter( int encounterSize){

        //System.out.println("Goblin Initiatives");
        for (int i = 0; i < encounterSize; i++) {
            combatArrayList.add(new Goblin());
            //System.out.println(encounter[i].getInit());
        }
    }
    
    // Populating Party Group
    private void newParty(int partySize){
        //System.out.println("Party Initiatives");
        for (int i = 0; i < partySize; i++) {
            combatArrayList.add(new Fighter());
            //System.out.println(party[i].getInit());
        }
    }

    // Checking If Individual from Group is Alive
    public boolean checkAlive(Creature individual ){
        return individual.isAlive();
    }
    
    // Checking If Group is Alive

    public boolean checkGroupAlive(ArrayList<Creature> group) {

        boolean players = false;
        boolean monsters = false;

        for (Creature g : group) {
            if (g instanceof Player) {
                if(g.isAlive()){
                    players = true;
                }
            }
            if (g instanceof Monster) {
                if(g.isAlive()){
                    monsters = true;
                }
            }
        }
        return players && monsters;
    }

   /* public boolean checkGroupAlive(ArrayList<Creature> group) {
        for (Creature g : group) {
            if (g.isAlive()) {
                return g.isAlive();
            }
        }
        return false;
    }*/
    
    // Determine if attack is stronger than reciever's armor class
    public boolean checkHit(Creature attacker, Creature defender){
        if (attacker.attack() >= defender.getAc()){
            return true;
        }
        return false;
    }
    
    // Generate Hit
    public void hit(Creature attacker, Creature defender) {
        defender.receiveDamage(attacker.attackDamage());
    }

    public void combat(){

        for (Creature c: combatArrayList) {
            c.generateInitiative();
        }

        Collections.sort(combatArrayList);
        Collections.reverse(combatArrayList);


        // do inititive
        // todo implement initiative
        
        while (checkGroupAlive(combatArrayList)) {

            // do a round
            round();
        }
    }

    public void singleCombat(Creature attacker, Creature defender) {
        // If attack is stronger than armor class, generate hit
        if( checkHit(attacker,defender)){
            hit(attacker,defender);
        }
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


        // if a initi is higher than d init, then a goes ffirst. If there is no d, then next a goes.

        for ( Creature a: combatArrayList) {

            if(!a.isAlive()){
                continue;
            }

            if(a instanceof Player){
                // attacks monsters

                for ( Creature d: combatArrayList) {
                    if(!d.isAlive()){
                        continue;
                    }
                    if (d instanceof Monster){
                        singleCombat(a,d);
                        break;
                    }
                }
            }

            if(a instanceof Monster){
                // attacks players
                for ( Creature d: combatArrayList) {
                    if(!d.isAlive()){
                        continue;
                    }
                    if (d instanceof Player){
                        singleCombat(a,d);
                        break;
                    }
                }
            }

        }




      //  combat(encounter,party);


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

        //combat(party,encounter);

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

    private void whoWon(){
        boolean players = false;
        boolean monsters = false;

        for (Creature g : combatArrayList) {
            if (g instanceof Player) {
                if(g.isAlive()){
                    players = true;
                }
            }
            if (g instanceof Monster) {
                if(g.isAlive()){
                    monsters = true;
                }
            }
        }

        if (players){
            winNum++;
        }
    }

    public void simulation() {

        for (int i = 0; i < simIterations; i++) {

            newEncounter(encounterSize);
            newParty(partySize);

            combat();
            whoWon();
/*            if(checkGroupAlive(party)){
                winNum++;
            }*/

            combatArrayList.clear();
        }

       calcWinRate(winNum, simIterations);

        System.out.println("Win Rate: " + String.format("%.2f", winRate) +"%");

    }
}


// todo 1: combat between individuals


// todo 2: combat between groups
