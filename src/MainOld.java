import monster.Monster;
import simulation.Sim;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import simulation.JsonTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static common.Commands.selectClass;

public class MainOld {

    public static void main(String[] args) {

//       JsonTest test = new JsonTest();

        try {
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
            int simIterations = 10000;
            ArrayList<Monster> monstersArrayList = new ArrayList<>();
            int monsterNumber;

            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            File files = new File("files/monsters/jsonBigTest.json");
            Monster[] monsters = mapper.readValue(files, Monster[].class);


            Scanner reader = new Scanner(System.in);  // Reading from System.in

            //* Party Selection Section *//*
            System.out.println("Party Selection");

            //* Refactored Class and Character Selection *//*
            fighters = selectClass("Fighter");

            rogues = selectClass("Rogue");

            clerics = selectClass("Cleric");

            wizards = selectClass("Wizard");

            //* Encounter Selection Section *//*

            for (int i = 0; i < monsters.length; i++) {
                System.out.println("Please type in the number of " +
                        monsters[i].getName() + "s in the encounter.");
                monsterNumber = reader.nextInt();

                for (int j = 0; j < monsterNumber; j++) {
                    monstersArrayList.add(monsters[i]);
                }
            }

            Sim sim = new Sim(fighters, rogues, clerics, wizards, monstersArrayList, simIterations);
            sim.simulation();

        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }

/*        System.out.println("\nEncounter Selection");
        System.out.print("Please type in number of Goblins in the encounter: ");
        goblinSize = reader.nextInt();
        System.out.print("Please type in number of Orcs in the encounter: ");
        orcSize = reader.nextInt();
        System.out.print("Please type in number of Bugbears in the encounter: ");
        bugbearSize = reader.nextInt();
        System.out.print("Please type in the number of simulations: ");
        simIterations = reader.nextInt();*/

    static public int[] selectClass(String c) {
        int[] classArray;
        int size;
        int level;

        Scanner reader = new Scanner(System.in);

        /* Character Selection */
        System.out.print("Please type in number of " + c + "s in your party: ");
        size = reader.nextInt();
        classArray = new int[size];

        /* Character Level Selection */
        for (int x = 0; x < classArray.length; x++) {
            do {
                System.out.print(c + ' ' + (x + 1) + " Level: ");
                level = reader.nextInt();

                if (level > 0 || level < 21) {
                    classArray[x] = level;
                } else {
                    System.out.println("Invalid Level, Try Again");
                }
            } while (level < 1 || level > 20);
        }
        // Return array
        return classArray;
    }
}
