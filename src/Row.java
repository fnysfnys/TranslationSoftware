import java.util.ArrayList;

public class Row {
    private String swedishValue;
    private String englishValue;

    public Row(String swedishValue, String englishValue){
        this.swedishValue = swedishValue;
        this.englishValue = englishValue;
    }

    public void setSwedishValue(String newValue){
        this.swedishValue = newValue;
    }

    public void setEnglishValue(String newValue){
        this.swedishValue = newValue;
    }

    public String getSwedishValue() {
        return swedishValue;
    }

    public String getEnglishValue() {
        return englishValue;
    }
}
