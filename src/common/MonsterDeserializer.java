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

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        name = (String) jsonNode.get("name").asText();

        if(jsonNode.get("ac").get(0).isInt()) {
            ac = (Integer) jsonNode.get("ac").get(0).numberValue();
        }
        else {
            ac = (Integer) jsonNode.get("ac").get(0).get("ac").numberValue();
        }



        monster = new Monster(name,ac,10,10,10,10,
                10,10,10,10,10,10,"2d8",10);
        return monster;
    }
}
