package common;

import monster.Monster;
import org.jetbrains.annotations.NotNull;
import player.Player;

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

    static public int hpPercent(Creature creature){
        return (creature.getHp()/creature.getMaxHp()) * 100;
    }

     static public int genAttribute() {
         ArrayList<Integer> diceRolls = new ArrayList<Integer>();
         int attr = 0;


         for (int i = 0; i < 4; i++) {
             diceRolls.add(ThreadLocalRandom.current().nextInt(1,7));
         }

         Collections.sort(diceRolls);
         Collections.reverse(diceRolls);

         for (int i = 0; i < 3; i++ ) {
             attr = attr + diceRolls.get(i);
         }

         return attr;
     }

     static public Monster lowestMonHP(@NotNull ArrayList<Creature> combatList){

         Monster lowest = null;
         // attack

         // For loop searches for Monster with lowest HP to attack.
         for (Creature c: combatList) {
             if (c instanceof Monster) {
                 if(!c.isAlive()) {       // checking to see if dead
                     continue;
                 }
                 if (lowest == null) {    // sets initial low
                     lowest = (Monster)c;
                 }
                 else {
                     if (lowest.getHp() >= c.getHp()){
                         lowest = (Monster)c;        // sets new lowest
                     }
                 }
             }
         }
        return lowest;
     }


    static public Player lowestPlayHP(@NotNull ArrayList<Creature> combatList){

        Player lowest = null;
        // attack

        // For loop searches for Monster with lowest HP to attack.
        for (Creature c: combatList) {
            if (c instanceof Player) {
                if(!c.isAlive()){       // checking to see if dead
                    continue;
                }
                if (lowest == null) {    // sets initial low
                    lowest = (Player) c;
                }
                else {
                    if (lowest.getHp() >= c.getHp()) {
                        lowest = (Player) c;        // sets new lowest
                    }
                }
            }
        }
        return lowest;
    }

    static public Monster highestMonHP(@NotNull ArrayList<Creature> combatList){

        Monster highest = null;
        // attack

        // For loop searches for Monster with higest HP to attack.
        for (Creature c: combatList) {
            if (c instanceof Monster) {
                if(!c.isAlive()) {       // checking to see if dead
                    continue;
                }
                if (highest == null) {    // sets initial high
                    highest = (Monster)c;
                }
                else {
                    if (highest.getHp() <= c.getHp()){
                        highest = (Monster)c;        // sets new highest
                    }
                }
            }
        }
        return highest;
    }

}
