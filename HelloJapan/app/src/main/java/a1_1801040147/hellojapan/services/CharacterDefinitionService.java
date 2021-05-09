package a1_1801040147.hellojapan.services;

import android.content.Context;

import a1_1801040147.hellojapan.models.CharacterDefinition;
import a1_1801040147.hellojapan.utils.FileIO;
import a1_1801040147.hellojapan.utils.JSONParser;

import org.json.JSONException;

public class CharacterDefinitionService {

    private static CharacterDefinitionService instance;

    private CharacterDefinitionService() {}

    public static CharacterDefinitionService getInstance() {
        if (instance == null)
            instance = new CharacterDefinitionService();
        return instance;
    }

    /**
     * @return an array of {@link CharacterDefinition}
     * @throws Exception if the json asset is corrupted
     * */
    public CharacterDefinition[] getAllCharacterDefinitions(Context context, String assetName) throws Exception {
        String json = FileIO.loadStringFromAsset(context, assetName);

        CharacterDefinition[] result;
        try {
            result = JSONParser.parseToCharacterDefinitions(json);
        } catch (JSONException e) {
            throw new Exception("Fatal: Corrupted json file");
        }
        return result;
    }
}
