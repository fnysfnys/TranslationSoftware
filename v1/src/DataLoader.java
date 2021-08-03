import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


public class DataLoader {
    private String filePath;

    public DataLoader(String filePath) {
        this.filePath = filePath;

    }

    public void loadDataToFrame(DataFrame dataFrame) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XWPFDocument document = new XWPFDocument(fis);
            dataFrame.setSourceDocxFilePath(filePath);
            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph paragraph : paragraphs) {
                String paragraphText = paragraph.getText();
                String[] sentences = paragraphText.split("\\. ");
                for (String sentence:sentences){
                    addSentenceToFrame(sentence, dataFrame);
                }
            }
            fis.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadMemoryToFrame(DataFrame dataFrame){

    }

    private void addSentenceToFrame(String sentence, DataFrame dataFrame) {
        dataFrame.addRow(sentence, "");
    }
}
