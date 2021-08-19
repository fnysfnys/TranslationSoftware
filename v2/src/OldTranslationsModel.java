import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OldTranslationsModel {
    private XWPFDocument originalDocument;
    private XWPFDocument translatedDocument;

    private TranslationMemory translationMemory;
    private String memoryFilePath;

    private DataFrame dataFrame;

    public OldTranslationsModel(){
        dataFrame = new DataFrame();
    }

    public void setOriginalDocument(XWPFDocument originalDocument){
        this.originalDocument = originalDocument;
    }

    public void setTranslatedDocument(XWPFDocument translatedDocument){
        this.translatedDocument = translatedDocument;
    }

    public void setTranslationMemory(String memoryFilePath, TranslationMemory translationMemory) {
        this.memoryFilePath = memoryFilePath;
        this.translationMemory = translationMemory;
    }

    public boolean readyToLaunch() {
        return ((originalDocument!=null) &&
                (translatedDocument!=null) &&
                (translationMemory!=null));
    }

    public void loadDocumentsToFrame() {
        OldTranslationsDataLoader dataLoader = new OldTranslationsDataLoader(originalDocument, translatedDocument);
        dataLoader.loadDocumentsToFrame(dataFrame);
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

    public void saveTranslationsToMemory(JTable translationTable) {
        getTableState(translationTable);
        getTranslationsFromFrame();

        try{
            writeMemoryToFile();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void writeMemoryToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(memoryFilePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(translationMemory);

        oos.close();
    }

    private void getTranslationsFromFrame() {
        int rowCount = dataFrame.size();
        Row currentRow;
        for (int row = 0; row < rowCount; row++) {
            currentRow = dataFrame.getRow(row);
            translationMemory.addTranslation(currentRow.getOriginal(), currentRow.getTranslation());
        }
    }

    private void getTableState(JTable translationTable) {
        int rowCount = translationTable.getRowCount();
        Row currentRow;
        for (int row = 0; row < rowCount; row++) {
            currentRow = dataFrame.getRow(row);
            currentRow.setOriginal((String) translationTable.getValueAt(row, 0));
            currentRow.setTranslation((String) translationTable.getValueAt(row, 1));
        }
    }

    public void updateFrame(JTable translationTable) {
        int rowCount = dataFrame.size();
        Row currentRow;
        for (int row = 0; row < rowCount; row++) {
            currentRow = dataFrame.getRow(row);
            currentRow.setOriginal((String) translationTable.getValueAt(row, 0));
            currentRow.setTranslation((String) translationTable.getValueAt(row, 1));
        }
    }

    public void deleteOriginalFromRow(int selectedRow) {
        dataFrame.deleteOriginalFromRow(selectedRow);
    }

    public void deleteTranslatedFromRow(int selectedRow) {
        dataFrame.deleteTranslationFromRow(selectedRow);
    }

    public void insertBlankTranslation(int selectedRow) {
        dataFrame.insertBlankTranslation(selectedRow);
    }

    public void insertBlankOriginal(int selectedRow) {
        dataFrame.insertBlankOriginal(selectedRow);
    }
}
