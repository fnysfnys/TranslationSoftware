import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Exporter
{
    private String filePath;
    private ArrayList<String> translatedDoc;

    public Exporter(String filePath, ArrayList<String> translatedDoc)
    {
        this.filePath = filePath;
        this.translatedDoc = translatedDoc;
    }

    public void exportFile() throws IOException
    {
        XWPFDocument newDocument = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(filePath);
        for (String line : translatedDoc)
        {
            XWPFParagraph paragraph = newDocument.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(line);
        }
        newDocument.write(out);
        out.close();
    }
}