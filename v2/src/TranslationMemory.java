import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslationMemory implements Serializable {

    private HashMap<String, String> translationMemory;

    private final String direction;

    private static final String NUMBER_KEY = "53b0a1b2fadf4e040cdc2155a7340de24aca93cb";

    private static final String SWEDISH_NUMBER = "(([\\+|-]?([0-9][0-9][0-9][0-9]|[0-9][0-9][0-9]|[0-9][0-9]|[0-9])([ |\\.][0-9][0-9][0-9])*)([,][0-9]+)?)";

    private static final String ENGLISH_NUMBER = "(([\\+|-]?([0-9][0-9][0-9][0-9]|[0-9][0-9][0-9]|[0-9][0-9]|[0-9])([,][0-9][0-9][0-9])*)([.][0-9]+)?)";

    private final Pattern SwedishNumberPattern = Pattern.compile(SWEDISH_NUMBER);

    private final Pattern EnglishNumberPattern = Pattern.compile(ENGLISH_NUMBER);

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

    private String translationWithNewNumbers(String original, String translation) {
        Matcher matcher;

        if(direction.equals("Swedish -> English")){
            matcher = SwedishNumberPattern.matcher(original);
        }
        else{
            matcher = EnglishNumberPattern.matcher(original);
        }

        ArrayList<String> newNumbers = extractNumbers(matcher);

        for(String newNumber : newNumbers){ translation = translation.replaceFirst(NUMBER_KEY, newNumber); }

        return translation;
    }

    private ArrayList<String> extractNumbers(Matcher matcher) {
        ArrayList<String> newNumbers = new ArrayList<>();
        while(matcher.find()){
            newNumbers.add(matcher.group());
        }
        return newNumbers;
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
