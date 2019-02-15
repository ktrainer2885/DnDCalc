import simulation.Sim;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int fighterSize;
        int clericSize;
        int goblinSize;
        int orcSize;
        int simIterations;


        Scanner reader = new Scanner(System.in);  // Reading from System.in

        System.out.print("Choose your Party!");
        System.out.print("Please type in the fighter size: ");
        fighterSize = reader.nextInt();
        System.out.print("Please type in the cleric size: ");
        clericSize = reader.nextInt();
        System.out.print("Please type in the goblin size: ");
        goblinSize = reader.nextInt();
        System.out.print("Please type in the Orc size: ");
        orcSize = reader.nextInt();
        System.out.print("Please type in the number of simulations: ");
        simIterations = reader.nextInt();

        Sim sim = new Sim(fighterSize, clericSize, goblinSize , orcSize, simIterations);
        sim.simulation();
    }
}
