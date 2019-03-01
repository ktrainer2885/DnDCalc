import simulation.Sim;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int partySize;
        int fighterSize;
        int rogueSize;
        int clericSize;
        int wizardSize;
        int goblinSize;
        int orcSize;
        int bugbearSize;
        int simIterations;


        Scanner reader = new Scanner(System.in);  // Reading from System.in

        System.out.println("Party Selection");
        System.out.print("Please type in the number of Fighters in your party: ");
        fighterSize = reader.nextInt();
        System.out.print("Please type in number of Rogues in your party: ");
        rogueSize = reader.nextInt();
        System.out.print("Please type in number of Clerics in your party: ");
        clericSize = reader.nextInt();
        System.out.print("Please type in number of Wizards in your party: ");
        wizardSize = reader.nextInt();

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
        
        Sim sim = new Sim(fighterSize, rogueSize, clericSize, wizardSize, goblinSize , orcSize,
                bugbearSize, simIterations);
        sim.simulation();
    }
}
