import simulation.Sim;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int partySize;
        int encounterSize;
        int simIterations;


        Scanner reader = new Scanner(System.in);  // Reading from System.in

        System.out.print("Please type in the party size: ");
        partySize = reader.nextInt();
        System.out.print("Please type in the encounter size: ");
        encounterSize = reader.nextInt();
        System.out.print("Please type in the number of simulations: ");
        simIterations = reader.nextInt();

        Sim sim = new Sim(partySize,encounterSize, simIterations);
        sim.simulation();
    }
}
