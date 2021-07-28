import java.io.Serializable;

public class Row implements Serializable {
    private String fromValue;
    private String toValue;

    public Row(String swedishValue, String englishValue){
        this.fromValue = swedishValue;
        this.toValue = englishValue;
    }

    public void setFromValue(String newValue){
        this.fromValue = newValue;
    }

    public void setToValue(String newValue){
        this.toValue = newValue;
    }

    public String getFromValue() {
        return fromValue;
    }

    public String getToValue() {
        return toValue;
    }
}
