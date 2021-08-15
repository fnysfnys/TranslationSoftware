
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;

public class TextReplacer {

    public static void main(final String[] args)
    {
        XWPFDocument originalDocument = new XWPFDocument();
        DOCXReader docxReader = new DOCXReader("/Users/gabrielturner/OneDrive - University College London/Summer Projects/translationSoftware/v2/original.docx");
        try {
            originalDocument = docxReader.getDocument();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        DataFrame dataFrame = new DataFrame();

        DataLoader dataLoader = new DataLoader(originalDocument);

        dataLoader.loadDocumentToFrame(dataFrame, new TranslationMemory("English -> Swedish"));

        XWPFDocument newDocument = originalDocument;

        List<XWPFParagraph> paragraphsToReplace = newDocument.getParagraphs();

        List<String> newParagraphs = dataFrame.getTranslatedParagraphs();

        for (int i = 0; i < Math.min(newParagraphs.size(), paragraphsToReplace.size()); i++) {
            replaceParagraph(paragraphsToReplace.get(i), newParagraphs.get(i));
        }

        FileOutputStream fo;
        try {
            fo = new FileOutputStream("/Users/gabrielturner/OneDrive - University College London/Summer Projects/translationSoftware/v2/exported.docx");
            newDocument.write(fo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void replaceParagraph(XWPFParagraph oldParagraph, String newParagraph) {
        List<XWPFRun> runs = oldParagraph.getRuns();
        if(runs.size() > 0) {
            String font = runs.get(0).getFontFamily();
            int fontSize = runs.get(0).getFontSize();
            boolean isBold = runs.get(0).isBold();

            int size = oldParagraph.getRuns().size();
            for (int i = 0; i < size; i++) {
                oldParagraph.removeRun(0);
            }

            String[] replacementTextSplitOnCarriageReturn = newParagraph.split("\n");
            for (int j = 0; j < replacementTextSplitOnCarriageReturn.length; j++) {
                String part = replacementTextSplitOnCarriageReturn[j];

                XWPFRun newRun = oldParagraph.insertNewRun(j);
                newRun.setFontFamily(font);
                newRun.setFontSize(fontSize);
                newRun.setText(part);
                newRun.setBold(isBold);

                if (j + 1 < replacementTextSplitOnCarriageReturn.length) {
                    newRun.addCarriageReturn();
                }
            }
        }
    }
}