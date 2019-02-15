package common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Commands {
    
    static public int rollX(int x) {
        return ThreadLocalRandom.current().nextInt(1,(x+1));
    }
    static public int roll10() {
        return ThreadLocalRandom.current().nextInt(1,11);
    }
     static public int roll20(){
         return ThreadLocalRandom.current().nextInt(1,21);
     }
     
     static public int disadvantage() {
         int [] rolls = new int[2];
         
         for (int x : rolls)
             rolls[x] = roll20();
         
         if (rolls[0] > rolls[1])
             return rolls[0];
         else
             return rolls[1];
         
     }

     static public int rollX(int x) { return ThreadLocalRandom.current().nextInt(1,(x+1)); }

     static public int advantage() {
         int roll1 = roll20();
         int roll2 = roll20();

         if(roll1 > roll2){
             return roll1;
         }
         else {
             return roll2;
         }

     }

     static public int rollHP(int diceNum, int diceType) {
         int hp = 0;

         for (int i = 0; i < diceNum; i++) {
             hp += ThreadLocalRandom.current().nextInt(1, diceType + 1);
         }

         return hp;
     }

     static public int rollHP(int diceNum, int diceType, int constant) {
         return  rollHP(diceNum, diceType) + constant;
     }

     static public int genAttribute() {
         ArrayList<Integer> diceRolls = new ArrayList<Integer>();
         int attr = 0;


         for (int i = 0; i < 4; i++){
             diceRolls.add(ThreadLocalRandom.current().nextInt(1,7));
         }

         Collections.sort(diceRolls);
         Collections.reverse(diceRolls);

         for (int i = 0; i < 3; i++ ) {
             attr = attr + diceRolls.get(i);
         }

         return attr;
     }



}
