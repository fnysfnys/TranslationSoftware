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
        List<XWPFParagraph> paragraphsToReplace = newDocument.getParagraphs();
        List<String> newParagraphs = dataFrame.getTranslatedParagraphs();

        for (int i = 0; i < Math.min(newParagraphs.size(), paragraphsToReplace.size()); i++) {
            replaceParagraph(paragraphsToReplace.get(i), newParagraphs.get(i));
        }

        write(filePath, newDocument);
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
                if(fontSize!=-1) {
                    newRun.setFontSize(fontSize);
                }
                newRun.setText(part);
                newRun.setBold(isBold);

                if (j + 1 < replacementTextSplitOnCarriageReturn.length) {
                    newRun.addCarriageReturn();
                }
            }
        }
    }

    private void write(String filePath, XWPFDocument newDocument) {
        FileOutputStream fo;
        try {
            fo = new FileOutputStream(filePath);
            newDocument.write(fo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
