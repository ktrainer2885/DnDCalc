package spells;

import static common.Commands.rollX;

public class WizardSpells {

    static public int magicMissile(){
        return rollX(4) + 1;
    }

    static public int magicMissile(int level) {
        int damage = 0;

        for (int i = 0; i < 3 + level - 1; i++) {
            damage += magicMissile();
        }

        return  damage;
    }

    // implement higher level variants
    static public int fireBolt(int level) {
        return rollX(10);
    }
}
