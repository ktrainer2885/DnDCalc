package player;

import common.Creature;
import monster.Monster;

import java.util.ArrayList;

import static common.Ability.FightingStyle;
import static common.Ability.secondWind;
import static common.Commands.*;

public class Fighter extends Player {
    
    protected int style;
    protected int secondWindUses;

    public Fighter() {

        this.style = FightingStyle();
        genAttributes();
        this.hp = 10 + getConMod(); // starting HP
        this.maxHp = hp;
        this.prof = 2; // Starting Bonus Profeciency
        this.weap = "1d8"; //Longsword damage
        this.ac = 18 + defense(); // chainmail and shield
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();
        this.secondWindUses = 1;
        this.level = 1;
        
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

        // 2nd wind is a bonus action can still attack. Only does it if less than 50% life left
        if( 50 > hpPercent() && secondWindUses > 0){
            recieveHealing(secondWind(10,level));
        }

        // attack
        Monster lowest = lowestMonHP(combatList);

        // If there is no monster, just return, else attack
        if (lowest == null) {
            return;
        }
        else {
            singleCombat(lowest);
        }
    }
}
