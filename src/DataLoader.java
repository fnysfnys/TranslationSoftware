import java.io.File;
import java.io.FileInputStream;
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
        String[] names = new String[0];

    }

    public void readData() {
//        WordExtractor extractor = null;
//        try {
//            File file = new File(filePath);
//            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
//
//            HWPFDocument doc = new HWPFDocument(fis);
//
//            WordExtractor we = new WordExtractor(doc);
//
//            String[] paragraphs = we.getParagraphText();
//
//            System.out.println("Total no of paragraph "+paragraphs.length);
//            for (String para : paragraphs) {
//                System.out.println(para.toString());
//            }
//            fis.close();
//        } catch (Exception e) {
//            e.printStackTrace();
    //}
    }
}
