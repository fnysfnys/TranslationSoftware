import java.io.Serializable;

public class Row implements Serializable {
    private String original;
    private String translation;
    private final int paragraphIndex;

    public Row(String original, String translation, int paragraphIndex){
        this.original = original;
        this.translation = translation;
        this.paragraphIndex = paragraphIndex;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getParagraphIndex(){
        return paragraphIndex;
    }
}
