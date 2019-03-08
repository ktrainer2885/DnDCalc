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

            File files = new File("files/monsters/jsonBigTest.json");
            System.out.println(files.getCanonicalPath());
            monsters = mapper.readValue(files, Monster[].class);

            for ( Monster m: monsters
                 ) {
                System.out.println(m.getName());
                System.out.println("AC: " + m.getAc());
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
