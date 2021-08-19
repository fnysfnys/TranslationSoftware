import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DOCXReader {

    private String filePath;

    public DOCXReader(String filePath){
        this.filePath = filePath;
    }

    public XWPFDocument getDocument() throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        return (new XWPFDocument(fis));
    }
}
