package a1_1801040147.hellojapan.utils;

import a1_1801040147.hellojapan.models.CharacterDefinition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
    /**
     * @throws JSONException if the input string is an invalid json array
     * @return an array of {@link CharacterDefinition}
     */
    public static CharacterDefinition[] parseToCharacterDefinitions(String json) throws JSONException {

        CharacterDefinition[] characterDefinitions;

        JSONArray arr = new JSONArray(json);

        characterDefinitions = new CharacterDefinition[arr.length()];

        for (int i = 0; i < arr.length(); i++) {
            JSONObject object = (JSONObject) arr.get(i);

            Object jpCharacterObj = object.get("jp");

            if (jpCharacterObj == JSONObject.NULL) {
                characterDefinitions[i] = new CharacterDefinition(null, null, null);
            } else {
                String jpCharacter = (String) jpCharacterObj;
                String spelling = object.getString("vn");
                String audioFileName = object.getString("name");
                CharacterDefinition definition = new CharacterDefinition(jpCharacter, spelling, audioFileName);

                characterDefinitions[i] = definition;
            }
        }
        return characterDefinitions;
    }
}
