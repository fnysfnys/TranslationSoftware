import java.io.Serializable;
import java.util.*;

public class DataFrame implements Serializable {

    private final ArrayList<Row> dataFrame;

    private String selectedMemoryPath;
    private String sourcePath;

    public DataFrame(){
        dataFrame = new ArrayList<>();
    }

    //getters and setters

    public String getSelectedMemoryPath() {
        return selectedMemoryPath;
    }

    public void setSelectedMemoryPath(String selectedMemoryPath) {
        this.selectedMemoryPath = selectedMemoryPath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    //getters and setters

    public int size(){
        return dataFrame.size();
    }

    public void addRow(String original, String translation, int paragraphIndex) {
        dataFrame.add(new Row(original, translation, paragraphIndex));
    }

    public Row getRow(int row) {
        return dataFrame.get(row);
    }

    //functions for loading old translations {

    public void deleteOriginalFromRow(int selectedRow){
        Row currentRow;
        for (int row = selectedRow; row < size()-1; row++) {
            currentRow = getRow(row);
            currentRow.setOriginal(getRow(row+1).getOriginal());
        }
        getRow(size()-1).setOriginal("");
    }

    public void deleteTranslationFromRow(int selectedRow){
        Row currentRow;
        for (int row = selectedRow; row < size()-1; row++) {
            currentRow = getRow(row);
            currentRow.setTranslation(getRow(row+1).getOriginal());
        }
        getRow(size()-1).setOriginal("");
    }


    public void insertBlankTranslation(int selectedRow) {
        Row currentRow;
        String prevTranslation = getRow(selectedRow).getTranslation();
        String currentTranslation;
        getRow(selectedRow).setTranslation("");
        for (int row = selectedRow + 1; row < size(); row++) {
            currentRow = getRow(row);
            currentTranslation = currentRow.getTranslation();
            currentRow.setTranslation(prevTranslation);
            prevTranslation = currentTranslation;
        }
    }

    public void insertBlankOriginal(int selectedRow) {
        Row currentRow;
        String prevOriginal = getRow(selectedRow).getOriginal();
        String currentOriginal;
        getRow(selectedRow).setOriginal("");
        for (int row = selectedRow + 1; row < size(); row++) {
            currentRow = getRow(row);
            currentOriginal = currentRow.getOriginal();
            currentRow.setOriginal(prevOriginal);
            prevOriginal = currentOriginal;
        }
    }

    // } functions for loading old translations

    // Functions for exporting

    public List<String> getTranslatedParagraphs() {
        ArrayList<String> translatedParagraphs = new ArrayList<>();
        StringBuilder currentParagraph = new StringBuilder();
        String currentSentence;
        int currentParagraphIndex = 0;
        Row currentRow;
        for (int row = 0; row < size(); row++) {
            currentRow = getRow(row);
            currentSentence = getCurrentSentence(currentRow);
            if(currentParagraphIndex == currentRow.getParagraphIndex()){
                currentParagraph.append(currentSentence + ". ");
            }
            else{
                translatedParagraphs.add(currentParagraph.toString());
                currentParagraph = new StringBuilder();
                currentParagraphIndex++;
            }
        }
        return translatedParagraphs;
    }

    private String getCurrentSentence(Row currentRow) {
        if(currentRow.getTranslation().equals("")){
            return currentRow.getOriginal();
        }
        else {
            return currentRow.getTranslation();
        }
    }
}
