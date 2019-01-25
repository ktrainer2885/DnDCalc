package common;

import java.util.concurrent.ThreadLocalRandom;

public class Commands {

     static public int roll20(){
         return ThreadLocalRandom.current().nextInt(1,21);
     }

}
