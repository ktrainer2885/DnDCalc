package player;

import common.Creature;

import java.util.ArrayList;

import static common.Commands.genAttribute;

public class Wizard extends Player {

    public Wizard(){

        genAttributes();
        this.hp = 6 + getConMod(); // starting HP
        this.prof = 2; // Starting Bonus Proficiency
        this.weap = "1d6";  // Quarterstaff bludgeoning
        this.ac = 10 + this.getDexMod();  // No armor: Base AC
        setDamageDice();
        this.alive = true;
        this.damConst = getStrMod();
    }

    @Override
    public void genPriorityAttributes() {
        
    }

    @Override
    public void chooseAction(ArrayList<Creature> combatList) {

    }
}
