package player;

import common.Creature;
import monster.Monster;

import java.util.ArrayList;

import static common.Ability.FightingStyle;
import static common.Commands.genAttribute;

public class Fighter extends Player {
    
    protected int style;

    public Fighter() {

        this.style = FightingStyle();
        genAttributes();
        this.hp = 10 + getConMod(); // starting HP
        this.prof = 2; // Starting Bonus Profeciency
        this.weap = "1d8"; //Longsword damage
        this.ac = 18 + defense(); // chainmail and shield
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();
        
    }
        
    // Modifier for AC if Fighter Style is Defense
    public int defense() {
        if (this.style == 2)
            return 1;
        else
            return 0;
    }

    @Override
    public void genPriorityAttributes() {

    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {

        Monster lowest = null;
        // attack

        // For loop searches for Monster with lowest HP to attack.
        for (Creature c: combatList) {
            if (c instanceof Monster) {
                if(!c.isAlive()){       // checking to see if dead
                    continue;
                }
                if (lowest == null){    // sets initial low
                    lowest = (Monster)c;
                }
                else{
                    if (lowest.getHp() >= c.getHp()){
                        lowest = (Monster)c;        // sets new lowest
                    }
                }
            }
        }

        // If there is no monster, just return, else attack
        if (lowest == null) {
            return;
        }
        else {
            singleCombat(lowest);
        }
    }
}
