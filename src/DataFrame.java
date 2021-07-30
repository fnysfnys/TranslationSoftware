import java.io.Serializable;
import java.util.ArrayList;

public class DataFrame implements Serializable {
    private ArrayList<Row> dataFrame;

    private String sourceDocxFilePath;

    private String projectFilePath;

    public DataFrame(){
        dataFrame = new ArrayList<>();
    }

    public void addRow(String swedishValue, String englishValue){
        Row row = new Row(swedishValue, englishValue);
        dataFrame.add(row);
    }

    public int getRowCount() {
        return dataFrame.size();
    }

    public void setSourceDocxFilePath(String sourceDocxFilePath){
        this.sourceDocxFilePath = sourceDocxFilePath;
    }

    public String getSourceDocxFilePath(){
        return sourceDocxFilePath;
    }

    public void setProjectFilePath(String projectFilePath){
        this.projectFilePath = projectFilePath;
    }

    public String getProjectFilePath() {
        return projectFilePath;
    }

    public boolean autoSavable(){
        return projectFilePath != null;
    }

    public Row getRow(int index){
        return dataFrame.get(index);
    }
}
