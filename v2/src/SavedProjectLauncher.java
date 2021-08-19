import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SavedProjectLauncher {
    Model model;
    public SavedProjectLauncher(){
        model = ModelFactory.getNewModel();
    }

    public void LaunchSavedProject(String projectPath){
        DataFrame dataFrame;
        TranslationMemory translationMemory;
        XWPFDocument document;

        try {
            dataFrame = loadDataFrameFromFile(projectPath);
            model.setProjectPath(projectPath);
            model.setDataFrame(dataFrame);

            translationMemory = loadTranslationMemoryFromFile(dataFrame.getSelectedMemoryPath());
            model.setTranslationMemory(translationMemory);

            document = loadDocumentFromFile(dataFrame.getSourcePath());
            model.setDocument(document);
        }
        catch (Exception e){
            //handle files broken/not found!
        }
    }

    private XWPFDocument loadDocumentFromFile(String sourcePath) throws IOException {
        DOCXReader docxReader = new DOCXReader(sourcePath);
        return docxReader.getDocument();
    }

    private TranslationMemory loadTranslationMemoryFromFile(String selectedMemoryPath) throws IOException, ClassNotFoundException {
        MemoryReader memoryReader = new MemoryReader(selectedMemoryPath);
        return memoryReader.getTranslationMemory();
    }

    private DataFrame loadDataFrameFromFile(String projectPath) throws IOException, ClassNotFoundException {
        DataFrameReader dataFrameReader = new DataFrameReader(projectPath);
        return dataFrameReader.getDataFrame();
    }
}
