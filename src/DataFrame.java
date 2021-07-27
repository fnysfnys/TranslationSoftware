import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Row> dataFrame;
    private XWPFDocument document;

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

    public void setCurrentDocument(XWPFDocument document){
        this.document = document;
    }

    public void setMemoryFilePath(XWPFDocument document){
        this.memoryFilePath = memoryFilePath;
    }

    public Row getRow(int index){
        return dataFrame.get(index);
    }
}
