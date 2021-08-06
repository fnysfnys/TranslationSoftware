import java.io.Serializable;
import java.util.HashMap;

public class TranslationMemory implements Serializable {

    private HashMap<String, String> translationMemory;

    private String direction;

    private static final String NUMBER_KEY = "53b0a1b2fadf4e040cdc2155a7340de24aca93cb";

    private static final String SWEDISH_NUMBER = "(([\\+|-]?([0-9][0-9][0-9][0-9]|[0-9][0-9][0-9]|[0-9][0-9]|[0-9])([ |\\.][0-9][0-9][0-9])*)([,][0-9]+)?)";

    private static final String ENGLISH_NUMBER = "(([\\+|-]?([0-9][0-9][0-9][0-9]|[0-9][0-9][0-9]|[0-9][0-9]|[0-9])([,][0-9][0-9][0-9])*)([.][0-9]+)?)";

    public TranslationMemory(String direction){
        translationMemory = new HashMap<>();
        this.direction = direction;
    }

    public void addTranslation(String original, String translation){

        String originalWithoutNumbers = removeNumbersFromOriginal(original);
        String translationWithoutNumbers = removeNumbersFromTranslation(translation);

        if(translationExists(original)){
            updateTranslation(original, translation);
        }else{
            translationMemory.put(original, translation);
        }
    }

    private void updateTranslation(String original, String translation){
        translationMemory.remove(original);
        addTranslation(original, translation);
    }

    public boolean translationExists(String original){
        String originalWithoutNumbers = removeNumbersFromOriginal(original);
        return translationMemory.containsKey(originalWithoutNumbers);
    }

    public String getTranslation(String original){
        return translationMemory.get(original);
    }


    private String removeNumbersFromOriginal(String original) {
        if (this.direction.equals("Swedish -> English")) {
            return original.replaceAll(SWEDISH_NUMBER, NUMBER_KEY);

        } else {
            return original.replaceAll(ENGLISH_NUMBER, NUMBER_KEY);
        }
    }

    private String removeNumbersFromTranslation(String translation) {
        if (this.direction.equals("Swedish -> English")) {
            return translation.replaceAll(ENGLISH_NUMBER, NUMBER_KEY);
        } else {
            return translation.replaceAll(SWEDISH_NUMBER, NUMBER_KEY);
        }
    }
}
