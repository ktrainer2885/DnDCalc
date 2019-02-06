package common;

import static common.Commands.rollX;
import java.util.Scanner;

public class Ability {
    
    static public int FightingStyle() {
        
        int style = 0;
        /*
        Scanner in = new Scanner(System.in);
        
        System.out.println("1. Archery");
        System.out.println("2. Defense");
        System.out.println("3. Dueling");
        System.out.println("4. Great Weapon Fighting");
        System.out.println("5. Protection");
        System.out.println("6. Two-Weapon Fighting");
        System.out.print("Choose Fighting Style: ");
        */
        
        while (true) {
            //style = in.nextInt();
            style = rollX(6);
            switch(style) {
                case 1:             // Archery
                    return 1;
                case 2:             // Defense
                    return 2;
                case 3:             // Dueling
                    return 3;
                case 4:             // Great Weapon Fighting
                    return 4;
                case 5:             // Protection
                    return 5;
                case 6:             // Two-Weapon Fighting
                    return 6;
                default:            
                    System.out.println("Invalid Choice");
            }
        }
    }
    
    public int SecondWind() {
        
        return 0;
    }
    
}
