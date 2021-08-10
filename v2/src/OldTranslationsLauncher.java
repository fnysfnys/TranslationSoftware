import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OldTranslationsLauncher {
    private OldTranslationsModel model;
    public OldTranslationsLauncher(){
        model = OldTranslationsModelFactory.getModel();
    }

    public void serOriginalDocument(String filePath) {
        DOCXReader docxReader = new DOCXReader(filePath);
        try {
            model.setOriginalDocument(docxReader.getDocument());
        }
        catch (Exception exp){
            exp.printStackTrace();
            //MUST HANDLE IF CAN NOT READ DOCUMENT
        }
    }

    public void setTranslatedDocument(String filePath) {
        DOCXReader docxReader = new DOCXReader(filePath);
        try {
            model.setTranslatedDocument(docxReader.getDocument());
        }
        catch (Exception exp){
            exp.printStackTrace();
            //MUST HANDLE IF CAN NOT READ DOCUMENT
        }
    }

    public void setMemory(String filePath) {
        try{
            loadMemory(filePath);
        }
        catch (Exception e){
            //MUST HANDLE CORRUPTED FILE.
            e.printStackTrace();
        }
    }

    private void loadMemory(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);

        TranslationMemory translationMemory = (TranslationMemory) ois.readObject();

        ois.close();

        model.setTranslationMemory(translationMemory);
    }

    public boolean readyToLaunch() {
        return model.readyToLaunch();
    }
}
