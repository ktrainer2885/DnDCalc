package common;

import static common.Commands.rollHP;
import static common.Commands.rollX;

public class Ability {
    
    static public int FightingStyle() {
        
        int style = 0;
        
        while (true) {
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
                default:             // Two-Weapon Fighting
                    return 6;
            }
        }
    }
    
    static public int secondWind(int diceType, int level) {
        return rollHP(1, diceType) + level;
    }
    
}
