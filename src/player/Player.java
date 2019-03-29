package player;

import common.Creature;

import static common.Commands.genAttribute;

public abstract class Player extends Creature {


    public void genAttributes() {
        setStr(genAttribute());
        setDex(genAttribute());
        setCon(genAttribute());
        setIntel(genAttribute());
        setWis(genAttribute());
        setCha(genAttribute());
    }

    public abstract void levelUp();

    public abstract void genPriorityAttributes();
}
