import simulation.Sim;

import java.util.Scanner;

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

    static public int[] selectClass(String c) {
        int[] classArray;
        int size;
        int level;

        Scanner reader = new Scanner(System.in);

        /* Character Selection */
        System.out.print("Please type in number of "+c+"s in your party: ");
        size = reader.nextInt();
        classArray = new int[size];

        /* Character Level Selection */
        for (int x = 0; x < classArray.length; x++) {
            do {
                System.out.print(c + ' ' + (x + 1) + " Level: ");
                level = reader.nextInt();

                if (level > 0 || level < 21) {
                    classArray[x] = level;
                }
                else {
                    System.out.println("Invalid Level, Try Again");
                }
            } while(level < 1 || level > 20);
        }
        // Return array
        return classArray;
    }
}
