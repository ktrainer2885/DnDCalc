package simulation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import monster.Monster;

import java.io.File;
import java.io.IOException;

public class JsonTest {
    ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    Monster monster;
    Monster[] monsters;

    {
        try {
            File file = new File("files/monsters/test.json");
            System.out.println(file.getCanonicalPath());
            monster = mapper.readValue(file, Monster.class);
            System.out.println(monster.getName());
            System.out.println(monster.getAc());
            System.out.println(monster.getHp());
            System.out.println(monster.getProf());
            System.out.println(monster.attackDamage());

            File files = new File("files/monsters/jsonBigTest.json");
            System.out.println(files.getCanonicalPath());
            monsters = mapper.readValue(files, Monster[].class);

            for ( Monster m: monsters
                 ) {
                System.out.println();
                System.out.println("Name: " + m.getName());
                System.out.println("AC: " + m.getAc());
                System.out.println("HP: " + m.getHp());
                System.out.println("Prof: " + m.getProf());
                System.out.println("Damage Dealt: " +m.attackDamage());
                System.out.println("STR: " + m.getStr());
                System.out.println("DEX: " + m.getDex());
                System.out.println("CON: " + m.getCon());
                System.out.println("INT: " + m.getIntel());
                System.out.println("WIS: " + m.getWis());
                System.out.println("CHA: " + m.getCha());
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
