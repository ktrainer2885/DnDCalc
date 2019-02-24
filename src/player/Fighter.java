package player;

import common.Creature;

import static common.Ability.FightingStyle;
import static common.Commands.genAttribute;

public class Fighter extends Player {
    
    protected int style;

    public Fighter() {

        this.style = FightingStyle();
        genAttributes();
        this.hp = 10 + getConMod(); // starting HP
        this.prof = 2; // Starting Bonus Proficiency
        this.weap = "1d8"; //Longsword damage
        this.ac = 18 + defense(); // chain mail and shield
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
}
