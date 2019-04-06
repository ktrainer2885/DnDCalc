package player;

import common.Creature;
import item.*;

import static common.Commands.genAttribute;

public abstract class Player extends Creature {

    protected Ammunition ammunition;
    protected Armor armor;
    protected Weapon weapon;

    public void genAttributes() {
        setStr(genAttribute());
        setDex(genAttribute());
        setCon(genAttribute());
        setIntel(genAttribute());
        setWis(genAttribute());
        setCha(genAttribute());
    }

    public void setAmmunition(Ammunition ammunition) {
        this.ammunition = ammunition;
    }

    public Ammunition getAmmunition() {
        return this.ammunition;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Armor getArmor() {
        return this.armor;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public abstract void setArmorClass();

    public abstract void setWeaponRoll();

    public abstract void levelUp();

    public abstract void genPriorityAttributes();
}
