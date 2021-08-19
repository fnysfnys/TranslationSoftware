import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataFrameReader {

    private String dataFramePath;

    public DataFrameReader(String dataFramePath){
        this.dataFramePath = dataFramePath;
    }

    public DataFrame getDataFrame() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(dataFramePath);
        ObjectInputStream ois = new ObjectInputStream(fis);

        DataFrame dataFrame = (DataFrame) ois.readObject();

        ois.close();

        return dataFrame;
    }
}