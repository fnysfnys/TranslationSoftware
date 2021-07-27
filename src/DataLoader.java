import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
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
            dataFrame.setCurrentDocument(document);
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
