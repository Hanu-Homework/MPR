package a1_1801040147.hellojapan.models;

public class CharacterDefinition {

    private String jpCharacter;

    private String spelling;

    private String audioFileName;

    public CharacterDefinition(String jpCharacter, String spelling, String audioFileName) {
        this.setJpCharacter(jpCharacter);
        this.setSpelling(spelling);
        this.setAudioFileName(audioFileName);
    }

    public String getJpCharacter() {
        return jpCharacter;
    }

    public void setJpCharacter(String jpCharacter) {
        this.jpCharacter = jpCharacter;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getAudioFileName() {
        return audioFileName;
    }

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }

    public boolean isBlank() {
        return this.jpCharacter == null;
    }
}
