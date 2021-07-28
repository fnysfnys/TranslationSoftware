import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class DataFrame implements Serializable {
    private ArrayList<Row> dataFrame;
    private String sourceFilePath;

    private String memoryFilePath;

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

    public void setSourceFilePath(String sourceFilePath){
        this.sourceFilePath = sourceFilePath;
    }

    public void setMemoryFilePath(String memFilePath){
        this.memoryFilePath = memFilePath;
    }

    public Row getRow(int index){
        return dataFrame.get(index);
    }
}
