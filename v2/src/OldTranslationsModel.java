import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class OldTranslationsModel {
    private XWPFDocument originalDocument;
    private XWPFDocument translatedDocument;

    private TranslationMemory translationMemory;
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

    public void setTranslationMemory(TranslationMemory translationMemory) {
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
}
