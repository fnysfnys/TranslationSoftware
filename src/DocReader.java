import java.io.*;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xwpf.usermodel.TextSegment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocReader
{
    public static void main(String[] args)
    {
        try {
                FileInputStream fis = new FileInputStream("/Users/gabrielturner/OneDrive - University College London/Summer Projects/translationSoftware/compare.docx");

                XWPFDocument document = new XWPFDocument(fis);

                List<XWPFParagraph> paragraphs = document.getParagraphs();



                for (XWPFParagraph para : paragraphs) {
                    para.getText();
                    System.out.println(para.getText());
                }
                fis.close();

        } catch (Exception e) {
                e.printStackTrace();
        }

    }
}