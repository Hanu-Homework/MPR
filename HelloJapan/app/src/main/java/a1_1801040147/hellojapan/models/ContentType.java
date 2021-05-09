package a1_1801040147.hellojapan.models;

public enum ContentType {
    HIRAGANA,
    KATAKANA;

    public String getAssetName() {
        switch (this) {
            case HIRAGANA:
                return "hiragana.json";
            case KATAKANA:
                return "katakana.json";
        }
        return null;
    }

    public String getName() {
        switch (this) {
            case KATAKANA:
                return "Katakana";
            case HIRAGANA:
                return "Hiragana";
        }
        return null;
    }
}
