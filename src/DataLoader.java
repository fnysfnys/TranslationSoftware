import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataLoader {
    private BufferedReader br;

    public DataLoader(String filePath) {
        try{
            br = new BufferedReader(new FileReader(filePath));
        }
        catch (IOException exp){
            exp.printStackTrace();
        }
    }

    public void loadDataToFrame(GUI gui, DataFrame dataFrame) {
        String[] names = new String[0];
        try {
            readData(dataFrame, names);
        }
        catch (IOException exp){
            exp.printStackTrace();
        }
    }

    private void readData(DataFrame dataFrame, String[] names) throws IOException {
        boolean firstLine = true;
        String line;
        String[] currentLine;
        while ((line = br.readLine()) != null) {

        }
    }
}
