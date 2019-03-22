import simulation.Sim;

import java.util.Scanner;

import static common.Commands.selectClass;

public class Main {

    public static void main(String[] args) {

        int size;
        // Array size is the amount of class specific characters within the party
        // Array data contains the level number
        // Change to Array List
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

        /* Refactored Class and Character Selection */
        fighters = selectClass("Fighter");

        rogues = selectClass("Rogue");

        clerics = selectClass("Cleric");

        wizards = selectClass("Wizard");

        /* Encounter Selection Section */
        System.out.println("\nEncounter Selection");
        System.out.print("Please type in number of Goblins in the encounter: ");
        goblinSize = reader.nextInt();
        System.out.print("Please type in number of Orcs in the encounter: ");
        orcSize = reader.nextInt();
        System.out.print("Please type in number of Bugbears in the encounter: ");
        bugbearSize = reader.nextInt();
        System.out.print("Please type in the number of simulations: ");
        simIterations = reader.nextInt();

        Sim sim = new Sim(fighters, rogues, clerics, wizards, goblinSize , orcSize,
                bugbearSize, simIterations);
        sim.simulation();
    }

}
