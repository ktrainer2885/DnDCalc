package common;

import common.Commands;
import java.util.Scanner;

public class Ability {
    
    static public int FightingStyle() {
        
        int style = 0;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("1. Archery");
        System.out.println("2. Defense");
        System.out.println("3. Dueling");
        System.out.println("4. Great Weapon Fighting");
        System.out.println("5. Protection");
        System.out.println("6. Two-Weapon Fighting");
        System.out.print("Choose Fighting Style: ");
        
        while (true) {
            
            style = in.nextInt();
            
            switch(style) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
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
