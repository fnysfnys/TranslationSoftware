import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.List;

public class DataLoader {
    private XWPFDocument document;
    public DataLoader(XWPFDocument document){
        this.document = document;
    }

    public void loadDocumentToFrame(DataFrame dataFrame, TranslationMemory translationMemory){
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        int paragraphIndex = 0;
        for(XWPFParagraph paragraph : paragraphs){
            loadParagraphToFrame(dataFrame, paragraphIndex, paragraph);
            paragraphIndex++;
        }
    }

    private void loadParagraphToFrame(DataFrame dataFrame, int paragraphIndex, XWPFParagraph paragraph) {
        String paragraphText = paragraph.getText();
        String[] sentences = paragraphText.split("\\. ");
        for (String sentence:sentences){
            dataFrame.addRow(sentence, "", paragraphIndex);
        }
    }
}
