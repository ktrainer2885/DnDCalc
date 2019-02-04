package player;

import common.Creature;

import static common.Commands.genAttribute;

public class Player extends Creature {

    protected int level;            // Class level



    public void genAttributes() {
        setStr(genAttribute());
        setDex(genAttribute());
        setCon(genAttribute());
        setIntel(genAttribute());
        setWis(genAttribute());
        setCha(genAttribute());
    }
}
