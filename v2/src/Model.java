import org.apache.poi.xwpf.usermodel.XWPFDocument;

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

    public void createMemory(String newMemoryFilePath) {
        translationMemory = new TranslationMemory();
        dataFrame.setSelectedMemoryPath(newMemoryFilePath);
    }


    public void setMemory(String filePath) {
        try{
            loadMemory(filePath);
        }
        catch (Exception e){
            //MUST HANDLE CORRUPTED FILE.
            e.printStackTrace();
        }
        dataFrame.setSelectedMemoryPath(filePath);
    }

    private void loadMemory(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);

        this.translationMemory = (TranslationMemory) ois.readObject();

        ois.close();
    }

    private void setSourceFilePath(String filePath) {
        dataFrame.setSourcePath(filePath);
    }

    public boolean readyToLaunch(){
        System.out.println(projectPath);
        System.out.println(dataFrame.getSelectedMemoryPath());
        System.out.println(this.document);
        return ((this.projectPath != null) &&
                (dataFrame.getSelectedMemoryPath() != null) &&
                (this.document != null));
    }

    public void getDocument(String filePath) {
       setSourceFilePath(filePath);

       DOCXReader docxReader = new DOCXReader(filePath);
       try {
           this.document = docxReader.getDocument();
       }
       catch (Exception exp){
           exp.printStackTrace();
           //MUST HANDLE IF CAN NOT READ DOCUMENT
        }
    }

    public void setProjectPath(String projectPath){
        this.projectPath = projectPath;
    }

    // } GET FILES
}
