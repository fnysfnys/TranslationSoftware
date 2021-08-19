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
        ArrayList<String> paragraphs = new ArrayList<>();
        for (int paragraphIndex = 0; paragraphIndex < getRow(size()-1).getParagraphIndex(); paragraphIndex++) {
            paragraphs.add(getParagraph(paragraphIndex));
        }
        return paragraphs;
    }

    private String getParagraph(int paragraphIndex) {
        Row currentRow;
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            currentRow = getRow(i);
            if(currentRow.getParagraphIndex() == paragraphIndex){
                if(currentRow.getTranslation().equals("")){
                    paragraph.append(currentRow.getOriginal());
                }
                else{
                    paragraph.append(currentRow.getTranslation());
                }
            }
        }
        return paragraph.toString();
    }


}
