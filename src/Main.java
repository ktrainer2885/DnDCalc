import simulation.Sim;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int size;
        // Array size is the amount of class specific characters within the party
        // Array data contains the level number
        int[] fighters;
        int[] rogues;
        int[] clerics;
        int[] wizards;
        int goblinSize;
        int orcSize;
        int bugbearSize;
        int simIterations;


        Scanner reader = new Scanner(System.in);  // Reading from System.in

        /* Party Selection Section */
        System.out.println("Party Selection");

        /* Fighter Selection */
        System.out.print("Please type in the number of Fighters in your party: ");
        size = reader.nextInt();
        fighters = new int[size];

        /* Fighter Level Selection */
        for (int x = 0; x< fighters.length; x++) {
            System.out.print("Fighter "+(x+1)+" Level: ");
            fighters[x] = reader.nextInt();
        }

        /* Rogue Selection */
        System.out.print("Please type in number of Rogues in your party: ");
        size = reader.nextInt();
        rogues = new int[size];

        /* Rogue Level Selection */
        for (int x = 0; x < rogues.length; x++) {
            System.out.print("Rogue "+(x+1)+" Level: ");
            rogues[x] = reader.nextInt();
        }

        /* Cleric Selection */
        System.out.print("Please type in number of Clerics in your party: ");
        size = reader.nextInt();
        clerics = new int[size];

        /* Cleric Level Selection */
        for (int x = 0; x < clerics.length; x++) {
            System.out.print("Cleric "+(x+1)+" Level: ");
            clerics[x] = reader.nextInt();
        }

        /* Wizard Selection */
        System.out.print("Please type in number of Wizards in your party: ");
        size = reader.nextInt();
        wizards = new int[size];

        /* Wizard Level Selection */
        for (int x = 0; x < wizards.length; x++) {
            System.out.print("Wizard "+(x+1)+" Level: ");
            wizards[x] = reader.nextInt();
        }

        /* Encounter Selection Section */
        System.out.println("\nEncounter Selection");
        System.out.print("Please type in number of Goblins in the encounter: ");
        goblinSize = reader.nextInt();
        System.out.print("Please type in number of Orcs in the encounter: ");
        orcSize = reader.nextInt();
        System.out.print("Please type in number of Bugbears in the encounter: ");
        bugbearSize = reader.nextInt();
        //System.out.print("Please type in the number of simulations: ");
        //simIterations = reader.nextInt();

        simIterations = 10000;
        
        Sim sim = new Sim(fighters, rogues, clerics, wizards, goblinSize , orcSize,
                bugbearSize, simIterations);
        sim.simulation();
    }
}
