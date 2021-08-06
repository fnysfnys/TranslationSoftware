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
            loadParagraphToFrame(dataFrame, paragraphIndex, paragraph, translationMemory);
            paragraphIndex++;
        }
    }

    private void loadParagraphToFrame(DataFrame dataFrame, int paragraphIndex, XWPFParagraph paragraph, TranslationMemory translationMemory) {
        String paragraphText = paragraph.getText();
        String[] sentences = paragraphText.split("\\. ");
        for (String sentence:sentences){
            if(translationMemory.translationExists(sentence)){
                dataFrame.addRow(sentence, translationMemory.getTranslation(sentence), paragraphIndex);
            }
            else {
                dataFrame.addRow(sentence, "", paragraphIndex);
            }
        }
    }
}
