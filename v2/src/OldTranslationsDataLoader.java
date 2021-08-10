import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OldTranslationsDataLoader {
    private final XWPFDocument originalDocument;
    private final XWPFDocument translatedDocument;

    private ArrayList<String> originalSentences;
    private ArrayList<String> translatedSentences;

    public OldTranslationsDataLoader(XWPFDocument originalDocument, XWPFDocument translatedDocument){
        this.originalDocument = originalDocument;
        this.translatedDocument = translatedDocument;
    }

    public void loadDocumentsToFrame(DataFrame dataFrame){
        originalSentences = loadDocumentToList(originalDocument);
        translatedSentences = loadDocumentToList(translatedDocument);

        loadSentencesToFrame(dataFrame);
    }

    private void loadSentencesToFrame(DataFrame dataFrame) {
        String currentOriginalSentence;
        String currentTranslatedSentence;
        for (int i = 0; i < Math.max(originalSentences.size(), translatedSentences.size()); i++) {
            if(i< originalSentences.size()){
                currentOriginalSentence = originalSentences.get(i);
            }
            else{
                currentOriginalSentence = "";
            }
            if(i< translatedSentences.size()){
                currentTranslatedSentence = translatedSentences.get(i);
            }
            else{
                currentTranslatedSentence = "";
            }

            dataFrame.addRow(currentOriginalSentence, currentTranslatedSentence, -1);
        }
    }

    private ArrayList<String> loadDocumentToList(XWPFDocument document) {
        ArrayList<String> documentSentences = new ArrayList<>();
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for(XWPFParagraph paragraph : paragraphs){
            String paragraphText = paragraph.getText();
            String[] paragraphSentences = paragraphText.split("\\. ");
            documentSentences.addAll(Arrays.asList(paragraphSentences));
        }
        return documentSentences;
    }
}
