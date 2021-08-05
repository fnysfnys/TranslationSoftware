import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.io.*;

public class Model {
    private String projectPath;

    private DataFrame dataFrame;
    private TranslationMemory translationMemory;
    private XWPFDocument document;


    public Model(){
        dataFrame = new DataFrame();
    }

    //GET FILES {

    public void setTranslationMemory(TranslationMemory translationMemory) {
        this.translationMemory = translationMemory;
    }

    public void setSelectedMemoryPath(String newMemoryFilePath) {
        dataFrame.setSelectedMemoryPath(newMemoryFilePath);
    }

    public void setSourcePath(String sourcePath) {
        dataFrame.setSourcePath(sourcePath);
    }

    public boolean readyToLaunch() {
        return ((this.projectPath != null) &&
                (dataFrame.getSourcePath() != null) &&
                (dataFrame.getSelectedMemoryPath() != null));
    }

    public void setDocument(XWPFDocument document) {
        this.document = document;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }


    // } GET FILES

    public void loadDocumentToFrame(){
        DataLoader dataLoader = new DataLoader(document);
        dataLoader.loadDocumentToFrame(dataFrame, translationMemory);
    }

    public String[][] getTableData() {
        int rowCount = dataFrame.size();
        String[][] tableData = new String[rowCount][2];
        Row currentRow;
        for (int row = 0; row < rowCount; row++) {
            currentRow = dataFrame.getRow(row);
            tableData[row][0] = currentRow.getOriginal();
            tableData[row][1] = currentRow.getTranslation();
        }
        return tableData;
    }

    public void autoSave(JTable translationTable) {
        int rowCount = dataFrame.size();
        String currentOriginal, currentTranslation;
        Row currentRow;
        for (int row = 0; row < rowCount; row++) {
            currentOriginal = (String) translationTable.getValueAt(row, 0);
            currentTranslation = (String) translationTable.getValueAt(row, 1);
            currentRow = dataFrame.getRow(row);

            currentRow.setTranslation(currentTranslation);
            translationMemory.addTranslation(currentOriginal, currentTranslation);
        }
    }

}
