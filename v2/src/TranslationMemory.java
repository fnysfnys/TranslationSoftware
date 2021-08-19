import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslationMemory implements Serializable {

    private HashMap<String, String> translationMemory;

    private final String direction;

    private static final String NUMBER_KEY = "53b0a1b2fadf4e040cdc2155a7340de24aca93cb";

    private static final String SWEDISH_NUMBER = "((([\\+|-]?([0-9][0-9][0-9][0-9]|[0-9][0-9][0-9]|[0-9][0-9]|[0-9])([ |\\.][0-9][0-9][0-9])*)([,][0-9]+)?)|( ett | två | tre | fyra | fem | sex | sju | åtta | nio | tio ))";

    private static final String ENGLISH_NUMBER = "((([\\+|-]?([0-9][0-9][0-9][0-9]|[0-9][0-9][0-9]|[0-9][0-9]|[0-9])([,][0-9][0-9][0-9])*)([.][0-9]+)?)|( one | two | three | four | five | six | seven | eight | nine | ten))";

    private final Pattern SwedishNumberPattern = Pattern.compile(SWEDISH_NUMBER);

    private final Pattern EnglishNumberPattern = Pattern.compile(ENGLISH_NUMBER);

    public TranslationMemory(String direction){
        translationMemory = new HashMap<>();
        this.direction = direction;
    }

    public void addTranslation(String original, String translation){
        if(!original.equals("") && !original.equals(" ")) {

            String originalWithoutNumbers = removeNumbersFromOriginal(original);
            String translationWithoutNumbers = removeNumbersFromTranslation(translation);

            System.out.println("translation: " + translation);
            System.out.println("translation wo numbers: " + translationWithoutNumbers);

            if (translationExists(originalWithoutNumbers)) {
                updateTranslation(originalWithoutNumbers, translationWithoutNumbers);
            } else {
                translationMemory.put(originalWithoutNumbers, translationWithoutNumbers);
            }
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

        for(String newNumber : newNumbers){
            translation = translation.replaceFirst(NUMBER_KEY, reformat(newNumber));
        }

        //now we handle hashes if a number isn't found

        translation = translation.replaceAll(NUMBER_KEY, "NUM_NOT_FOUND");

        return translation;
    }

    private String reformat(String newNumber) {
        if(direction.equals("Swedish -> English")){
            switch (newNumber) {
                case " ett ":
                    return " one ";
                case " två ":
                    return " two ";
                case " tre ":
                    return " three ";
                case " fyra ":
                    return " four ";
                case "fem":
                    return " five ";
                case " sex ":
                    return " six ";
                case " sju ":
                    return " seven ";
                case " åtta ":
                    return " eight ";
                case " nio ":
                    return " nine ";
                case " tio ":
                    return " ten ";
            }
        }
        else {
            switch (newNumber) {
                case " one ":
                    return " ett ";
                case " two ":
                    return " två ";
                case " three ":
                    return " tre ";
                case " four ":
                    return " fyra ";
                case " five ":
                    return " fem ";
                case " six ":
                    return " six ";
                case " seven ":
                    return " sju ";
                case " eight ":
                    return " åtta ";
                case " nine ":
                    return " nio ";
                case " ten ":
                    return " tio ";
            }

        }
        return reformatSeparatorsAndDecimals(newNumber);
    }

    private String reformatSeparatorsAndDecimals(String newNumber){
        if(direction.equals("Swedish -> English")){
            int charIndexOfDecimalPoint = -1;
            for (int i = 0; i < newNumber.length(); i++) {
                if(newNumber.charAt(i)==','){
                    charIndexOfDecimalPoint = i;
                    newNumber = newNumber.substring(0, i) + "." + newNumber.substring(i+1);
                }
            }

            for (int i = 0; i < newNumber.length(); i++) {
                if((newNumber.charAt(i)=='.' || newNumber.charAt(i)==' ') && (i != charIndexOfDecimalPoint)){
                    newNumber = newNumber.substring(0, i) + "," + newNumber.substring(i+1);
                }
            }
        }
        else{
            int charIndexOfDecimalPoint = -1;
            for (int i = 0; i < newNumber.length(); i++) {
                if(newNumber.charAt(i)=='.'){
                    charIndexOfDecimalPoint = i;
                    newNumber = newNumber.substring(0, i) + "," + newNumber.substring(i+1);
                }
            }

            for (int i = 0; i < newNumber.length(); i++) {
                if((newNumber.charAt(i)==',') && (i != charIndexOfDecimalPoint)){
                    newNumber = newNumber.substring(0, i) + " " + newNumber.substring(i+1);
                }
            }
        }
        return newNumber;
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
