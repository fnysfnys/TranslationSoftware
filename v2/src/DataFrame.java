import java.io.Serializable;
import java.util.ArrayList;

public class DataFrame implements Serializable {

    private ArrayList<Row> dataFrame;

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
}
