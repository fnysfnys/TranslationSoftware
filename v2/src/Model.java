import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class Model {
    private String projectPath;

    private DataFrame dataFrame;
    private TranslationMemory translationMemory;
    private XWPFDocument document;

    //GET FILES {

    public Model(){
        dataFrame = new DataFrame();
    }

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
}
