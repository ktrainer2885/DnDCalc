package simulation;

import common.Creature;
import monster.*;
import player.*;

import java.util.ArrayList;
import java.util.Collections;

public class Sim {

    private ArrayList<Creature> combatArrayList;

    private int partySize;
    private int[] fighterArray;
    private int[] rogueArray;
    private int[] clericArray;
    private int[] wizardArray;
    private int goblinSize;
    private int orcSize;
    private int bugbearSize;
    private int simIterations;
    private int winNum;
    private double winRate;

    public Sim(int[] fighterArray, int[] rogueArray, int[] clericArray, int[] wizardArray, int goblinSize, int orcSize,
               int bugbearSize, int simIterations){
        this.combatArrayList = new ArrayList<>();
        this.simIterations = simIterations;
        this.partySize = fighterArray.length + rogueArray.length + clericArray.length + wizardArray.length;
        this.fighterArray = fighterArray;
        this.rogueArray = rogueArray;
        this.clericArray = clericArray;
        this.wizardArray = wizardArray;
        this.goblinSize = goblinSize;
        this.orcSize = orcSize;
        this.bugbearSize = bugbearSize;
        this.winNum = 0;
    }
    
    // Populating Encounter Group
    private void newEncounter( int goblinSize, int orcSize, int bugbearSize){

        //System.out.println("Goblin Initiatives");
        for (int i = 0; i < goblinSize; i++) {
            combatArrayList.add(new Goblin());
            //System.out.println(encounter[i].getInit());
        }
        for (int i = 0; i < orcSize; i++) {
            combatArrayList.add(new Orc());
        }
        for (int i = 0; i < bugbearSize; i++) {
            combatArrayList.add(new Bugbear());
        }
    }

    // Populating Party Group
    private void newParty(int[] fighterArray, int[] rogueArray, int[] clericArray, int[] wizardArray){
        //System.out.println("Party Initiatives");
        for (int i = 0; i < fighterArray.length; i++) {
            combatArrayList.add(new Fighter());
        }
        for (int i = 0; i < rogueArray.length; i++){
            combatArrayList.add(new Rogue());
        }
        for (int i = 0; i < clericArray.length; i++){
            combatArrayList.add(new Cleric());
        }
        for (int i = 0; i < wizardArray.length; i++){
            combatArrayList.add(new Wizard());
        }
    }
    
    // Checking If a creature from both groups is Alive. Returns true if both alive. Used for combat loop
    public boolean checkGroupAlive(ArrayList<Creature> group) {

        boolean players = false;
        boolean monsters = false;

        for (Creature g : group) {
            if (g instanceof Player) {
                if(g.isAlive()) {
                    players = true;
                }
            }
            if (g instanceof Monster) {
                if(g.isAlive()) {
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
    
    public void setCombat() {

        for (Creature c: combatArrayList) {
            c.generateInitiative();
        }

        Collections.sort(combatArrayList);
        Collections.reverse(combatArrayList);


        // do initiative
        // todo implement initiative
        
        while (checkGroupAlive(combatArrayList)) {

            // do a round
            round();
        }
    }

   public void combat(ArrayList<Creature> attacker, ArrayList<Creature> defender) {

       for (Creature a : attacker) {
           if (!a.isAlive()) {
               break;
            }

           for (Creature d : defender) {
               if (d.isAlive()) {
                   // Calling the helper function located in the Creature Class
                   a.singleCombat(d);
                   break;
                }
            }
        }
    }

    public void round() {
        // if a initi is higher than d init, then a goes ffirst. If there is no d, then next a goes.

        for ( Creature a: combatArrayList) {

            if(!a.isAlive()) {
                continue;
            }

            //Checks if a player is attacking
            if(a instanceof Player) {
                // attacks monsters

/*                for ( Creature d: combatArrayList) {
                    if(!d.isAlive()){
                        continue;
                    }
                    if (d instanceof Monster){
                        a.singleCombat(d);
                        break;
                    }
                }*/

                a.chooseAction(combatArrayList);

            }

            //Checks if a monster is attacking
            if(a instanceof Monster) {
                // attacks players
                for ( Creature d: combatArrayList) {
                    if(!d.isAlive()) {
                        continue;
                    }
                    if (d instanceof Player) {
                        a.singleCombat(d);
                        break;
                    }
                }
            }
        }
    }

    // Calculates percentage win rate
    private void calcWinRate(int wins, int simIterations) {
        winRate = ((double)wins/simIterations) * 100;
    }

    // Checks who won the combat by looking for alive creatures. If only players alive, they get a win.
    private void whoWon() {
        boolean players = false;
        boolean monsters = false;

        for (Creature g : combatArrayList) {
            if (g instanceof Player) {
                if(g.isAlive()) {
                    players = true;
                }
            }
            if (g instanceof Monster) {
                if(g.isAlive()) {
                    monsters = true;
                }
            }
        }

        if (players && !monsters) {
            winNum++;
        }
    }

    // Starts the all the simulations. runs until simIteratinos is done.
    public void simulation() {

        for (int i = 0; i < simIterations; i++) {

            newEncounter(goblinSize, orcSize, bugbearSize);
            newParty(fighterArray,rogueArray,clericArray,wizardArray);
            //System.out.println(i);

            setCombat();
            whoWon();

            combatArrayList.clear();
        }

       calcWinRate(winNum, simIterations);

        System.out.println("\nWin Rate: " + String.format("%.2f", winRate) +"%");

    }
}
