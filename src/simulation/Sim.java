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

    private ArrayList<Monster> monstersArray;

    public Sim(int simIterations, ArrayList<Creature> combatArrayList){
        this.combatArrayList = combatArrayList;
        this.simIterations = simIterations;
        this.winNum = 0;
    }

    public Sim(int[] fighterArray, int[] rogueArray, int[] clericArray, int[] wizardArray,
               ArrayList<Monster> monstersArray, int simIterations){
        this.combatArrayList = new ArrayList<>();
        this.simIterations = simIterations;
        this.partySize = fighterArray.length + rogueArray.length + clericArray.length + wizardArray.length;
        this.fighterArray = fighterArray;
        this.rogueArray = rogueArray;
        this.clericArray = clericArray;
        this.wizardArray = wizardArray;
        //this.goblinSize = goblinSize;
        //this.orcSize = orcSize;
       // this.bugbearSize = bugbearSize;
        this.winNum = 0;

        this.monstersArray = monstersArray;
    }

    private void newSimulation(){

        for (Creature c: combatArrayList
             ) {
            c.setHp(c.getMaxHp());
        }
    }



    // Populating Encounter Group
    private void newEncounter(){

        for (Monster m : monstersArray) {

            m.setHp(m.getMaxHp());
            combatArrayList.add(m);
        }

/*        //System.out.println("Goblin Initiatives");
        for (int i = 0; i < goblinSize; i++) {
            combatArrayList.add(new Goblin());
            //System.out.println(encounter[i].getInit());
        }
        for (int i = 0; i < orcSize; i++) {
            combatArrayList.add(new Orc());
        }
        for (int i = 0; i < bugbearSize; i++) {
            combatArrayList.add(new Bugbear());
        }*/
    }

    /*
            Separate LevelUp from Creature
            Player player = new Fighter();
     */

    // Populating Party Group
    private void newParty(int[] fighterArray, int[] rogueArray, int[] clericArray, int[] wizardArray){
        //System.out.println("Party Initiatives");
        for (int i = 0; i < fighterArray.length; i++) {
            combatArrayList.add(new Fighter());
            combatArrayList.get(combatArrayList.size()-1).setLevel(fighterArray[i]);
            if (combatArrayList.get(combatArrayList.size()-1).getLevel() > 1) {
                ((Player)combatArrayList.get(combatArrayList.size()-1)).levelUp();
            }
        }
        for (int i = 0; i < rogueArray.length; i++){
            combatArrayList.add(new Rogue());
            combatArrayList.get(combatArrayList.size()-1).setLevel(rogueArray[i]);
            if (combatArrayList.get(combatArrayList.size()-1).getLevel() > 1) {
                ((Player)combatArrayList.get(combatArrayList.size()-1)).levelUp();
            }
        }
        for (int i = 0; i < clericArray.length; i++){
            combatArrayList.add(new Cleric());
            combatArrayList.get(combatArrayList.size()-1).setLevel(clericArray[i]);
            if (combatArrayList.get(combatArrayList.size()-1).getLevel() > 1) {
                ((Player)combatArrayList.get(combatArrayList.size()-1)).levelUp();
            }
        }
        for (int i = 0; i < wizardArray.length; i++){
            combatArrayList.add(new Wizard());
            combatArrayList.get(combatArrayList.size()-1).setLevel(wizardArray[i]);
            if (combatArrayList.get(combatArrayList.size()-1).getLevel() > 1) {
                ((Player)combatArrayList.get(combatArrayList.size()-1)).levelUp();
            }
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

            newEncounter();
            //newEncounter(goblinSize, orcSize, bugbearSize);
            newParty(fighterArray,rogueArray,clericArray,wizardArray);
            //System.out.println(i);

            setCombat();
            whoWon();

            combatArrayList.clear();
        }

       calcWinRate(winNum, simIterations);

        System.out.println("\nWin Rate: " + String.format("%.2f", winRate) +"%");

    }

    public double simulationWinRate(){
        for (int i = 0; i < simIterations; i++) {

            newSimulation();

            setCombat();
            whoWon();

        }

        calcWinRate(winNum, simIterations);

        return winRate;

    }

}
