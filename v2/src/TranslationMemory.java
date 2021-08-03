import java.io.Serializable;
import java.util.HashMap;

public class TranslationMemory implements Serializable {
    private HashMap<String, String> translationMemory;

    public TranslationMemory(){
        translationMemory = new HashMap<>();
    }


    public void addTranslation(String fromString, String toString){
        translationMemory.put(fromString, toString);
    }

    public void updateTranslation(String fromString, String toString){
        translationMemory.remove(fromString);
        addTranslation(fromString, toString);
    }

    public boolean translationExists(String fromString){
        return translationMemory.containsKey(fromString);
    }

    public String getTranslation(String fromString){
        return translationMemory.get(fromString);
    }
}
