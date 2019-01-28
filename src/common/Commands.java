package common;

import java.util.concurrent.ThreadLocalRandom;

public class Commands {

     static public int roll20(){
         return ThreadLocalRandom.current().nextInt(1,21);
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

}
