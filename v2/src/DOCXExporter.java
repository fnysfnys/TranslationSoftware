import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;
import java.io.FileOutputStream;
import java.util.List;

public class DOCXExporter {
    private XWPFDocument originalDocument;
    private DataFrame dataFrame;

    public DOCXExporter(XWPFDocument originalDocument, DataFrame dataFrame){
        this.originalDocument = originalDocument;
        this.dataFrame = dataFrame;
    }

    public void exportTranslation(String filePath){
        XWPFDocument newDocument = originalDocument;

        List<XWPFParagraph> originalParagraphs = newDocument.getParagraphs();
        List<String> newParagraphs = dataFrame.getTranslatedParagraphs();


        for (int i = 0; i < newParagraphs.size(); i++) {
            replaceParagraphText(originalParagraphs.get(i), newParagraphs.get(i));
        }




        FileOutputStream fo;
        try {
            fo = new FileOutputStream(filePath);
            newDocument.write(fo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void replaceParagraphText(XWPFParagraph originalParagraph, String newParagraphText) {
        List<XWPFRun> runs = originalParagraph.getRuns();
        removeAllRuns(originalParagraph);
        insertReplacementRuns(originalParagraph, newParagraphText);
    }

    private void insertReplacementRuns(XWPFParagraph paragraph, String replacedText) {
        String[] replacementTextSplitOnCarriageReturn = replacedText.split("\n");
        System.out.println(replacementTextSplitOnCarriageReturn.length);
        for (int j = 0; j < replacementTextSplitOnCarriageReturn.length; j++) {
            String part = replacementTextSplitOnCarriageReturn[j];

            XWPFRun newRun = paragraph.insertNewRun(j);
            newRun.setText(part);

            if (j + 1 < replacementTextSplitOnCarriageReturn.length) {
                newRun.addCarriageReturn();
            }
        }
    }

    private void removeAllRuns(XWPFParagraph paragraph) {
        int size = paragraph.getRuns().size();
        for (int i = 0; i < size; i++) {
            paragraph.removeRun(0);
        }
    }
}
