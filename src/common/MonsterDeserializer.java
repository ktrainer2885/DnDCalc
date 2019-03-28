package common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import monster.Monster;

import java.io.IOException;

public class MonsterDeserializer extends StdDeserializer<Monster> {

    public MonsterDeserializer() {
        this(null);
    }

    public MonsterDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Monster deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Monster monster;
        String name;
        Integer ac;
        Integer hpDiceNum;
        Integer hpDiceType;
        Integer hpConstant = 0;
        Integer prof = 0;
        Integer str;
        Integer dex;
        Integer con;
        Integer intel;
        Integer wis;
        Integer cha;
        String weapon = "0d0";
        String[] damDice;
        Integer damCont = 0;

        String hpFormula;
        String[] hpInfo; // 0  - num dice , 1 - dice type, 2 - hp constant
        String profString;
        String[] profStrings;


        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        name = (String) jsonNode.get("name").asText();

        if(jsonNode.get("ac").get(0).isInt()) {
            ac = (Integer) jsonNode.get("ac").get(0).numberValue();
        }
        else {
            ac = (Integer) jsonNode.get("ac").get(0).get("ac").numberValue();
        }

        // sets hp for monsters
        hpFormula = (String) jsonNode.get("hp").get("formula").asText();
        hpInfo = hpFormula.split("d|\\+|\\s");
        hpDiceNum = Integer.parseInt(hpInfo[0]);
        hpDiceType = Integer.parseInt(hpInfo[1]);
        if (hpInfo.length == 3){
            hpConstant = Integer.parseInt(hpInfo[2]);
        }

        // sets proficiency
        profString = (String) jsonNode.get("action").get(0).get("name").asText();
        if (profString.equalsIgnoreCase("multiattack")){
            profString = (String) jsonNode.get("action").get(1).get("entries").get(0).asText();
        }
        else {
            profString = (String) jsonNode.get("action").get(0).get("entries").get(0).asText();
        }

        //System.out.println(profString);
        profStrings = profString.split("@hit|@damage");

        for (int j = 0; j < profStrings.length; j++) {
            int i;
            i = profStrings[j].indexOf("}");
            if (i > -1){
                profStrings[j] = profStrings[j].substring(0,i);
                profStrings[j] = profStrings[j].trim();

                // adds in proficiency
                if (profStrings[j].matches("-?\\d+")){
                    prof = Integer.parseInt(profStrings[j]);
                }

                // adds in damage dice

                if(profStrings[j].matches("-?\\d+d\\d+\\s*(\\+\\s*\\d+)?")){
                    damDice = profStrings[j].split("\\+");
                    for (int k = 0; k < damDice.length; k++){
                        damDice[k] = damDice[k].trim();
                    }

                    weapon = damDice[0];
                    if(damDice.length > 1 ){
                        damCont = Integer.parseInt(damDice[1]);
                    }
                }

            }
            //System.out.println(profStrings[j]);
        }

        // Add in attributes

        str = (Integer) jsonNode.get("str").numberValue();
        dex = (Integer) jsonNode.get("dex").numberValue();
        con = (Integer) jsonNode.get("con").numberValue();
        intel = (Integer) jsonNode.get("int").numberValue();
        wis = (Integer) jsonNode.get("wis").numberValue();
        cha = (Integer) jsonNode.get("cha").numberValue();


        //prof = Integer.parseInt(profStrings[1]);
        monster = new Monster(name,ac,hpDiceNum,hpDiceType,hpConstant,prof,
                str,dex,con,intel,wis,cha,weapon,damCont);
        return monster;
    }

}
