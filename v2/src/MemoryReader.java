import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MemoryReader {

    private String memoryPath;

    public MemoryReader(String memoryPath){
        this.memoryPath = memoryPath;
    }

    public TranslationMemory getTranslationMemory() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(memoryPath);
        ObjectInputStream ois = new ObjectInputStream(fis);

        TranslationMemory translationMemory = (TranslationMemory) ois.readObject();

        ois.close();

        return translationMemory;
    }
}