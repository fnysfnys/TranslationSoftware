import java.io.Serializable;
import java.util.HashMap;

public class TranslationMemory implements Serializable {
    private HashMap<String, String> translationMemory;

    public TranslationMemory(){
        translationMemory = new HashMap<>();
    }

    public void addTranslation(String original, String translation){

        String originalWithoutNumbers = replaceNumsWithKey(original);

        if(translationExists(original)){
            updateTranslation(original, translation);
        }else{
            translationMemory.put(original, translation);
        }
    }

    private String replaceNumsWithKey(String original) {
        return original.replaceAll("(((\\+|-)?([0-9]+)(\\.[0-9]+)?)|((\\+|-)?\\.?[0-9]+))", "47959e21fa733a07fb9f31376ed25b418aaae516");
    }

    private void updateTranslation(String original, String translation){
        translationMemory.remove(original);
        addTranslation(original, translation);
    }

    private boolean translationExists(String original){
        String originalWithoutNumbers = replaceNumsWithKey(original);
        return translationMemory.containsKey(original);
    }

    public String getTranslation(String original){
        return translationMemory.get(original);
    }
}
