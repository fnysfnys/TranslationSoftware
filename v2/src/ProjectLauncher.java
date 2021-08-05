import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ProjectLauncher {

    private Model model;

    public ProjectLauncher(){
        model = ModelFactory.getModel();
    }

    public void setMemory(String filePath) {
        try{
            loadMemory(filePath);
        }
        catch (Exception e){
            //MUST HANDLE CORRUPTED FILE.
            e.printStackTrace();
        }
        model.setSelectedMemoryPath(filePath);
    }

    private void loadMemory(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);

        TranslationMemory translationMemory = (TranslationMemory) ois.readObject();

        ois.close();

        model.setTranslationMemory(translationMemory);
    }

    private void setSourceFilePath(String sourceFilePath) {
        model.setSourcePath(sourceFilePath);
    }

    public boolean readyToLaunch(){
        return model.readyToLaunch();
    }

    public void getDocument(String filePath) {
        setSourceFilePath(filePath);

        DOCXReader docxReader = new DOCXReader(filePath);
        try {
            model.setDocument(docxReader.getDocument());
        }
        catch (Exception exp){
            exp.printStackTrace();
            //MUST HANDLE IF CAN NOT READ DOCUMENT
        }
    }

    public void setProjectPath(String projectPath){
        model.setProjectPath(projectPath);
    }
}
