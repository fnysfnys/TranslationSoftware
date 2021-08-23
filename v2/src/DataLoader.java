import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

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
        String[] sentences = paragraphText.split("((?<=\\. ))");
        for (String sentence:sentences){
            String[] matchAndTranslation = getMatchIfAny(sentence, translationMemory);
            dataFrame.addRow(sentence, matchAndTranslation[0], matchAndTranslation[1], paragraphIndex);
        }
    }

    private String[] getMatchIfAny(String sentence, TranslationMemory translationMemory) {
        String[] matchAndTranslation = new String[2];
        if(translationMemory.translationExists(sentence)){
            matchAndTranslation[0] = "100%";
            matchAndTranslation[1] = translationMemory.getTranslation(sentence);
            return matchAndTranslation;
        }
        else{
            return translationMemory.getClosestMatch(sentence);
        }
    }
}
