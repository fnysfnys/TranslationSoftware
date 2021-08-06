import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if(translationExists(originalWithoutNumbers)){
            updateTranslation(originalWithoutNumbers, translationWithoutNumbers);
        }else{
            translationMemory.put(originalWithoutNumbers, translationWithoutNumbers);
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
        String originalWithoutNumbers = removeNumbersFromOriginal(original);
        String translationWithoutNumbers = translationMemory.get(originalWithoutNumbers);
        return translationWithNewNumbers(original, translationWithoutNumbers);
    }

    private String translationWithNewNumbers(String original, String translationWithoutNumbers) {
        if(direction.equals("Swedish -> English")){
            System.out.println(original);
            Pattern pattern = Pattern.compile(NUMBER_KEY);
            Matcher matcher = pattern.matcher(original);
            if(matcher.find()) {
                for (int i = 0; i < matcher.end(); i++) {
                    System.out.println(matcher.group(i));
                }
            }
        }
        return translationWithoutNumbers;
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
