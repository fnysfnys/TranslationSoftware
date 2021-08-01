import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TranslationMemory implements Serializable {
    private HashMap<String, String> translationMemory;
    private String filePath;

    public TranslationMemory(String filePath){
        this.filePath = filePath;
        translationMemory = new HashMap<>();
    }

    public void addTranslation(String fromString, String toString){
        translationMemory.put(fromString, toString);
    }

    public boolean inMemory(String fromString){
        return translationMemory.containsKey(fromString);
    }

    public String getTranslation(String fromString){
        return translationMemory.get(fromString);
    }

    public String getFilePath() {
            return filePath;
    }
}
