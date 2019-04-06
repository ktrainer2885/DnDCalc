package item;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import common.Item;
import common.WeaponDeserializer;


@JsonDeserialize(using = WeaponDeserializer.class)
public class Weapon extends Item {

    //private String technology;
    private Boolean weapon;
    private String weaponCategory;
    //private String age;
    private String dmg1;
    private String dmgType;
    private String[] property;
    private String range;
    private String reload;

    public Weapon() {}

    public Weapon(Weapon copy) {
        this.name = copy.name;
        this.type = copy.type;
        this.rarity = copy.rarity;
        this.weight = copy.weight;

        //this.technology = copy.technology;
        this.weapon = copy.weapon;
        this.weaponCategory = copy.weaponCategory;
        //this.age = copy.age;
        this.dmg1 = copy.dmg1;
        this.dmgType = copy.dmgType;
        //this.property = copy.property;
        this.range = copy.range;
        this.reload = copy.reload;
    }

    public Weapon(String name, String type, String rarity, String weight, /*String technology,*/
                  Boolean weapon, String weaponCategory, /*String age,*/ String dmg1,
                  String dmgType, /*String[] property,*/ String range, String reload) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.weight = weight;

        //this.technology = technology;
        this.weapon = weapon;
        this.weaponCategory = weaponCategory;
        //this.age = age;
        //this.dmg1 = dmg1;
        this.dmgType = dmgType;
        //this.property = property;
        this.range = range;
        this.reload = reload;
    }

    public String getWeaponRoll() {
        return this.dmg1;
    }

    @Override public String toString() {
        return name;
    }
}
